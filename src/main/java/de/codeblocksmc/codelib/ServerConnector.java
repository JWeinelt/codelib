package de.codeblocksmc.codelib;

import de.simonsator.partyandfriends.spigot.api.pafplayers.PAFPlayerManager;
import de.simonsator.partyandfriends.spigot.api.party.PartyManager;
import io.github.leonardosnt.bungeechannelapi.BungeeChannelApi;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * This class handles the connection of players to available servers, taking into account party-related restrictions.
 * It ensures that players cannot join a game while being in a party, unless they are the party leader.
 * The class communicates with BungeeCord via the {@link BungeeChannelApi} to search for available servers and connect players.
 * <p>
 * It also integrates with the PartyAndFriends plugin to check if the player is in a party and if they are the leader.
 * If the player is in a party but not the leader, they will not be able to join the server.
 * <p>
 * This class operates asynchronously, querying the BungeeCord server list and checking for server availability.
 *
 * @author JustCody
 * @version 1.0
 */
@Deprecated(forRemoval = true)
public class ServerConnector {

    // Prefix that is used for messages sent to players
    private final String prefix;

    // The plugin instance used for API calls and scheduling tasks
    private final JavaPlugin plugin;

    /**
     * Factory method for creating a new {@link ServerConnector} instance.
     *
     * @param plugin The plugin instance that will be used for task scheduling and API calls.
     * @param prefix The prefix to be used in messages sent to players.
     * @return A new {@link ServerConnector} instance.
     */
    public static ServerConnector of(JavaPlugin plugin, String prefix) {
        return new ServerConnector(plugin, prefix);
    }

    /**
     * Constructor for initializing the ServerConnector with a plugin and message prefix.
     *
     * @param plugin The plugin instance that will be used for task scheduling and API calls.
     * @param prefix The prefix to be used in messages sent to players.
     */
    protected ServerConnector(JavaPlugin plugin, String prefix) {
        this.prefix = prefix;
        this.plugin = plugin;
    }

    /**
     * Attempts to connect a player to a server, checking for available servers that match the provided server name.
     * It checks if the player is in a party and ensures that they are the party leader before allowing the connection.
     * <p>
     * The method will search for a server that is less than 30 players, and if found, it will connect the player to it.
     *
     * @param player The player to be connected.
     * @param servername The name of the server to connect to (partial match).
     */
    public void connect(Player player, String servername) {
        // Check if the player is in a party and not the leader
        if (PartyManager.getInstance().getParty(PAFPlayerManager.getInstance().getPlayer(player.getUniqueId())) != null &&
                !PartyManager.getInstance().getParty(PAFPlayerManager.getInstance().getPlayer(player.getUniqueId())).getLeader().equals(PAFPlayerManager.getInstance().getPlayer(player.getUniqueId()))) {
            player.sendMessage("§cSorry, you can't join a game while being in a party.");
            return;
        }

        // Notify the player that the search for a free server is starting
        player.sendMessage(prefix + "§aLooking for free server in §e" + servername + "§a...");

        // Get the BungeeChannelApi instance to interact with BungeeCord
        BungeeChannelApi api = BungeeChannelApi.of(plugin);

        // Run the connection task asynchronously
        new BukkitRunnable() {

            @Override
            public void run() {
                // Fetch the list of available servers
                api.getServers().whenComplete((result, error) -> {
                    AtomicBoolean found = new AtomicBoolean(false);

                    // Loop through the servers and check if one matches the specified servername
                    for (String server : result) {
                        if (!server.startsWith(servername)) continue;

                        // Get the player count for the current server
                        api.getPlayerCount(server).whenComplete((count, err) -> {
                            // If the server has less than 30 players, connect the player
                            if (count < 30) {
                                api.connect(player, server);
                                found.set(true);
                            }
                        });
                    }

                    // Notify the player if no free server was found after the search
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            try {
                                if (!found.get()) {
                                    player.sendMessage(prefix + "§7We could not find a free server. Sorry for the inconvenience!");
                                }
                            } catch (Exception e) {
                                // Handle any errors when notifying the player
                                plugin.getLogger().warning(e.getMessage());
                            }
                        }
                    }.runTaskLater(plugin, 5);  // Delay to allow for the server search to complete
                });
            }

        }.runTaskLater(plugin, 0);  // Run the task immediately
    }
}
