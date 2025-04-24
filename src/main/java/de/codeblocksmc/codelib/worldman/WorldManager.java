package de.codeblocksmc.codelib.worldman;

import de.codeblocksmc.codelib.locations.LocationWrapper;
import lombok.Getter;
import org.bukkit.*;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

@Getter
public class WorldManager {
    private final List<String> loadedWorlds = new ArrayList<>();
    private final JavaPlugin plugin;

    public WorldManager(JavaPlugin plugin) {
        this.plugin = plugin;
        for (World w : Bukkit.getWorlds()) loadedWorlds.add(w.getName());
    }

    public void registerCommand(String commandName) {
        plugin.getCommand(commandName).setExecutor(new WorldManCmd(this));
        plugin.getCommand(commandName).setTabCompleter(new WorldManCom(this));
        Bukkit.getPluginManager().addPermission(new Permission("codelib.wman.list"));
        Bukkit.getPluginManager().addPermission(new Permission("codelib.wman.add"));
        Bukkit.getPluginManager().addPermission(new Permission("codelib.wman.remove"));
        Bukkit.getPluginManager().addPermission(new Permission("codelib.wman.teleport"));
    }

    public void loadWorld(String name) {
        loadedWorlds.add(name);
        WorldCreator creator = new WorldCreator(name);
        creator.generator("VoidGen");
        World w = Bukkit.createWorld(creator);
        w.setDifficulty(Difficulty.PEACEFUL);
        w.setGameRule(GameRule.DO_MOB_SPAWNING, false);
    }

    public void unloadWorld(String name, boolean saveWorld) {
        loadedWorlds.remove(name);
        Bukkit.unloadWorld(name, saveWorld);
    }
}