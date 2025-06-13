package de.codeblocksmc.codelib.api.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.TabCompleter;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandBuilder {

    private final String name;
    private final List<String> arguments = new ArrayList<>();
    private String description = "";
    private String permission = null;
    private CommandExecutor executor;
    private TabCompleter tabCompleter = null;

    public CommandBuilder(String name) {
        this.name = name;
    }

    public CommandBuilder description(String description) {
        this.description = description;
        return this;
    }

    public CommandBuilder permission(String permission) {
        this.permission = permission;
        return this;
    }

    public CommandBuilder argument(String argName) {
        arguments.add(argName);
        return this;
    }

    public CommandBuilder executor(CommandExecutor executor) {
        this.executor = executor;
        return this;
    }

    public CommandBuilder tabCompleter(TabCompleter completer) {
        this.tabCompleter = completer;
        return this;
    }

    public void register(JavaPlugin plugin) {
        PluginCommand command = createPluginCommand(name, plugin);

        command.setDescription(description);
        if (permission != null) command.setPermission(permission);
        command.setExecutor((sender, cmd, label, args) -> {
            Map<String, String> mappedArgs = new HashMap<>();
            for (int i = 0; i < arguments.size() && i < args.length; i++) {
                mappedArgs.put(arguments.get(i), args[i]);
            }
            return executor != null && executor.onCommand(sender, mappedArgs);
        });

        if (tabCompleter != null) {
            command.setTabCompleter(tabCompleter);
        }

        CommandRegistry.registerCommand(plugin, command);
    }

    // Hacky Methode um PluginCommand zu erzeugen
    private PluginCommand createPluginCommand(String name, JavaPlugin plugin) {
        try {
            Constructor<PluginCommand> constructor = PluginCommand.class.getDeclaredConstructor(String.class, Plugin.class);
            constructor.setAccessible(true);
            return constructor.newInstance(name, plugin);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create command: " + name, e);
        }
    }

    public interface CommandExecutor {
        boolean onCommand(CommandSender sender, Map<String, String> args);
    }
}
