package de.codeblocksmc.codelib.api.database;

import de.codeblocksmc.codelib.api.database.template.StorageTemplate;
import lombok.Getter;
import org.bukkit.plugin.Plugin;

import java.util.WeakHashMap;

public class DBLib {
    private static WeakHashMap<Plugin, DBLib> instances = new WeakHashMap<>();

    private StorageTemplate storage;

    @Getter
    private DBLibConfig config;

    public DBLib(DBLibConfig config) {
        this.config = config;
    }


    public static synchronized DBLib of(Plugin plugin, DBLibConfig config, StorageTemplate storage) {
        if (instances.containsKey(plugin)) return instances.get(plugin);
        DBLib lib = new DBLib(config);
        lib.storage = storage;
        instances.put(plugin, lib);
        return lib;
    }

    public DBSchema getSchema(String name) {
        return storage.getSchema(name);
    }
}