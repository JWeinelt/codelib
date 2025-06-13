package de.codeblocksmc.codelib.api.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Field;

public class CommandRegistry {

    private static CommandMap getCommandMap() {
        try {
            Field commandMapField = Bukkit.getServer().getClass().getDeclaredField("commandMap");
            commandMapField.setAccessible(true);
            return (CommandMap) commandMapField.get(Bukkit.getServer());
        } catch (Exception e) {
            throw new RuntimeException("Unable to get CommandMap", e);
        }
    }

    public static void registerCommand(JavaPlugin plugin, PluginCommand command) {
        getCommandMap().register(plugin.getName(), command);
    }
}