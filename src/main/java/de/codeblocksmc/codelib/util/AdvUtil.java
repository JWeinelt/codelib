package de.codeblocksmc.codelib.util;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.advancement.Advancement;
import org.bukkit.entity.Player;

public class AdvUtil {
    public static void grantAdvancement(Player player, String advancement) {
        Advancement a = Bukkit.getAdvancement(new NamespacedKey("codeblocksmc", advancement));
        if (a == null) throw new IllegalArgumentException("Advancement " + advancement + " not found.");
        player.getAdvancementProgress(a).awardCriteria("m");
    }

    public static void grantAdvancement(Player player, Advancement advancement) {
        player.getAdvancementProgress(advancement).awardCriteria("m");
    }
    public static boolean hasAdvancementDone(Player player, String advancement) {
        Advancement a = Bukkit.getAdvancement(new NamespacedKey("codeblocksmc", advancement));
        return a != null && player.getAdvancementProgress(a).isDone();
    }
}