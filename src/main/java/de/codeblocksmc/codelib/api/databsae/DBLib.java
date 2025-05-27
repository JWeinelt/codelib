package de.codeblocksmc.codelib.api.databsae;


import lombok.Getter;
import org.bukkit.plugin.Plugin;

import java.util.WeakHashMap;

public class DBLib {
    private static WeakHashMap<Plugin, DBLib> instances = new WeakHashMap<>();

    @Getter
    private DBLibConfig config;

    public DBLib(DBLibConfig config) {
        this.config = config;
    }


    public static synchronized DBLib of(Plugin plugin, DBLibConfig config) {
        if (instances.containsKey(plugin)) return instances.get(plugin);
        DBLib lib = new DBLib(config);
        instances.put(plugin, lib);
        return lib;
    }
}