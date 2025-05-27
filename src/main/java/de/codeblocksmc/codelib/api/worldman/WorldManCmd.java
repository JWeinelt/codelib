package de.codeblocksmc.codelib.api.worldman;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class WorldManCmd implements CommandExecutor {
    private final WorldManager manager;

    public WorldManCmd(WorldManager manager) {
        this.manager = manager;
    }


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("Please use this command only in-game.");
            return false;
        }


        if (args.length == 0) {
            sender.sendMessage("§cInvalid usage! Possible arguments: list | unload | delete | create | teleport");
        } else if (args.length == 1) {
            if (args[0].equalsIgnoreCase("create")
                    || args[0].equalsIgnoreCase("unload")
                    || args[0].equalsIgnoreCase("delete")
                    || args[0].equalsIgnoreCase("teleport")) {
                sender.sendMessage("§cPlease provide a name!");
            } else if (args[0].equalsIgnoreCase("list")) {
                sender.sendMessage("§6§l§m===========");
                sender.sendMessage("§b§lWorlds");
                for (String s : manager.getLoadedWorlds()) {
                    sender.sendMessage("§6"+s+"§7 >> §aLOADED");
                }
            } else {
                sender.sendMessage("§cInvalid arguments provided.");
            }
        } else if (args.length == 2) {
             if (args[0].equalsIgnoreCase("create")) {
                 sender.sendMessage("§bCreating world §e" + args[1] + "...");
                 manager.loadWorld(args[1]);
                 sender.sendMessage("§aDone!");
             }
            if (Bukkit.getWorld(args[1]) == null && !args[1].equalsIgnoreCase("create")) {
                sender.sendMessage("§cThe world §e" + args[1] + "§c seems not to exist. Is there a typo?");
                return false;
            }
            if (args[0].equalsIgnoreCase("load")) {
                sender.sendMessage("§bLoading world §e" + args[1] + "...");
                manager.loadWorld(args[1]);
                sender.sendMessage("§aDone!");
            } else if (args[0].equalsIgnoreCase("unload")) {
                sender.sendMessage("§bUnloading world §e" + args[1] + "...");
                manager.unloadWorld(args[1], true);
                sender.sendMessage("§aDone!");
            } else if (args[0].equalsIgnoreCase("delete")) {
                sender.sendMessage("§cDeleting world §e" + args[1] + "...");
                manager.unloadWorld(args[1], false);
                sender.sendMessage("§aUnloaded. Continuing with§c deleting§a...");
                try {
                    Files.delete(new File(manager.getPlugin().getDataFolder().getParent(), args[1]).toPath());
                } catch (IOException e) {
                    sender.sendMessage("§cProblem while deleting world §e" + args[1] + "§c. Please look at the console for further information.");
                    return false;
                }
                sender.sendMessage("§aDone!");
            } else if (args[0].equalsIgnoreCase("teleport")) {
                player.sendMessage("§aTeleporting to §e" + args[1] + "§a...");
                player.teleport(Bukkit.getWorld(args[1]).getSpawnLocation());
            }
        }
        return false;
    }
}