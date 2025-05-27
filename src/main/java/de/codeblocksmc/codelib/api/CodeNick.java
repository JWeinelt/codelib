package de.codeblocksmc.codelib.api;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.WeakHashMap;

public class CodeNick {
    private static final WeakHashMap<Plugin, CodeNick> instances = new WeakHashMap<>();

    public synchronized static CodeNick of(Plugin plugin) {
        CodeNick c = new CodeNick();
        instances.put(plugin, c);
        return c;
    }

    public void nickPlayer(Player player) {

    }
}
