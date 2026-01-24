package de.codeblocksmc.codelib.util;

import io.github.leonardosnt.bungeechannelapi.BungeeChannelApi;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Pattern;

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
 * @author Try
 * @version 1.1
 */
public class ServerConnector {
    private final ConcurrentHashMap<Pattern, Integer> serverMaxPlayerCount = new ConcurrentHashMap<>();

    @Setter
    @Getter
    private int defaultMaxPlayerCount = 30;

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
     * @param player     The player to be connected.
     * @param serverPattern A pattern to match server names.
     * @param searchDescription The description which is show to the player when the searching is starting.
     */
    public void connect(Player player, Pattern serverPattern, String searchDescription) {

        //If the pattern is not known register it with the default player count.
        if (!serverMaxPlayerCount.containsKey(serverPattern))
            serverMaxPlayerCount.put(serverPattern, defaultMaxPlayerCount);


        player.sendMessage(prefix + "§aLooking for free server in §e" + searchDescription + "§a...");

        BungeeChannelApi api = BungeeChannelApi.of(plugin);

        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            api.getServers().whenComplete((result, error) -> {
                AtomicBoolean found = new AtomicBoolean(false);


                for (String server : result) {
                    if (!serverPattern.matcher(server).hasMatch()) continue;



                    api.getPlayerCount(server).whenComplete((count, err) -> {

                        if (count < serverMaxPlayerCount.get(serverPattern)) {
                            api.connect(player, server);
                            found.set(true);
                        }
                    });

                    //stop the search if a server is found
                    if(found.get())
                        break;
                }

                //There is no reason to start a task if the server is found.
                if (found.get()) return;

                Bukkit.getScheduler().runTaskLater(plugin, () -> {

                    try {
                        player.sendMessage(prefix + "§7We could not find a free server. Sorry for the inconvenience!");
                    } catch (Exception e) {
                        plugin.getLogger().warning(e.getMessage());
                    }

                }, 5);
            });

        }, 0);

    }


    public void setServerMaxPlayerCount(Pattern serverPattern, int playerCount) {
        this.serverMaxPlayerCount.put(serverPattern, playerCount);
    }
}
