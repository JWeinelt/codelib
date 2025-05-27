package de.codeblocksmc.codelib.api;

import com.destroystokyo.paper.profile.PlayerProfile;
import com.destroystokyo.paper.profile.ProfileProperty;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.WeakHashMap;

public class CodeNick {
    private static final WeakHashMap<Plugin, CodeNick> instances = new WeakHashMap<>();

    private SaveMethod saveMethod;

    public synchronized static CodeNick of(Plugin plugin, SaveMethod method) {
        CodeNick c = new CodeNick();
        instances.put(plugin, c);
        c.saving(method);
        return c;
    }

    public CodeNick saving(SaveMethod method) {
        saveMethod = method;
        return this;
    }

    public boolean nickPlayer(Player player, String targetName) {
        player.displayName(Component.text(targetName));

        PlayerProfile playerProfile = player.getPlayerProfile();
        try {
            URL url_0 = new URL("https://api.mojang.com/users/profiles/minecraft/" + targetName);

            InputStreamReader reader_0 = new InputStreamReader(url_0.openStream());

            String uuid = JsonParser.parseReader(reader_0).getAsJsonObject().get("id").getAsString();

            URL url_1 = new URL("https://sessionserver.mojang.com/session/minecraft/profile/" + uuid + "?unsigned=false");

            InputStreamReader reader_1 = new InputStreamReader(url_1.openStream());

            JsonObject properties = JsonParser.parseReader(reader_1).getAsJsonObject().get("properties").getAsJsonArray().get(0).getAsJsonObject();

            String value = properties.get("value").getAsString();
            String signature = properties.get("signature").getAsString();

            playerProfile.setProperty(new ProfileProperty("textures", value, signature));
            player.setPlayerProfile(playerProfile);

            return true;
        } catch (IllegalStateException | IOException | NullPointerException ignored) {
            return false;
        }
    }

    public enum SaveMethod {
        INTERNAL,
        MYSQL,
        SQLITE
    }
}