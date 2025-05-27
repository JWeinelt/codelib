package de.codeblocksmc.codelib;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class CodeLib extends JavaPlugin {
    @Getter
    private static final String version = "4.0.0";

    @Getter
    private static CodeLib instance;

    @Getter
    private Logger log;
    private PluginManager pluginManager;

    @Override
    public void onLoad() {
        instance = this;
    }

    @Override
    public void onEnable() {
        log.info("""
                   ____          _      _     _ _    \s
                  / ___|___   __| | ___| |   (_) |__ \s
                 | |   / _ \\ / _` |/ _ \\ |   | | '_ \\\s
                 | |__| (_) | (_| |  __/ |___| | |_) |
                  \\____\\___/ \\__,_|\\___|_____|_|_.__/\s
                                                      \
                """);
        log.info("Starting CodeLib v" + version);
        checkServerVersion();
        checkForCaesar();

    }

    @Override
    public void onDisable() {

    }

    private void checkServerVersion() {
        String version = Bukkit.getVersion().toLowerCase();

        if (version.contains("paper")) {
            log.info("Running on Paper v" + getServer().getMinecraftVersion());
        } else if (version.contains("spigot")) {
            log.info("Running on Spigot v" + getServer().getMinecraftVersion());
            log.warning("WARNING: _____________________________________________");
            log.warning("        |                                             |");
            log.warning("        | This server seems to be running SpigotMC!   |");
            log.warning("        | Some features may not work properly!        |");
            log.warning("        | Please use PAPER or PURPUR for the best     |");
            log.warning("        | Experience!!!                               |");
            log.warning("        | ___________________________________________ |");
        } else if (version.contains("purpur")) {
            log.info("Running on Purpur v" + getServer().getMinecraftVersion());
        } else if (version.contains("folia")) {
            log.info("Running on Folia v" + getServer().getMinecraftVersion());
        } else {
            log.warning("""
                         _____   \s
                        / / \\ \\  \s
                       / /| |\\ \\ \s
                      / / |_| \\ \\\s
                     /_/__(_)__\\_\\
                    
                    """);
            log.warning("WARNING: _____________________________________________");
            log.warning("        |                                             |");
            log.warning("        | This server is running an unknown software! |");
            log.warning("        | Expect broken features and bugs.            |");
            log.warning("        | Please use PAPER or PURPUR for the best     |");
            log.warning("        | Experience!!!                               |");
            log.warning("        | ___________________________________________ |");
        }
    }

    private void checkForCaesar() {
        log.info("Checking availability of Caesar...");
        if (pluginManager.getPlugin("CaesarConnector") != null) {
            log.info("Caesar has been detected. Registering internal expansions...");
        }
    }
}