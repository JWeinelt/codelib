package de.codeblocksmc.codelib.util;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.entity.Firework;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class FireworkBuilder {
    private int delay;
    private FireworkEffect.Builder effect;

    public FireworkBuilder(int delay) {
        this.delay = delay;
        effect = FireworkEffect.builder();
    }

    public FireworkBuilder color(Color color) {
        effect.withColor(color);
        return this;
    }

    public FireworkBuilder colors(Color... colors) {
        effect.withColor(colors);
        return this;
    }

    public FireworkBuilder fade(Color color) {
        effect.withFade(color);
        return this;
    }

    public FireworkBuilder fade(Color... colors) {
        effect.withFade(colors);
        return this;
    }

    public FireworkBuilder flicker() {
        effect.withFlicker();
        return this;
    }

    public FireworkBuilder trail() {
        effect.withTrail();
        return this;
    }

    public void spawn(Location loc, int power, JavaPlugin plugin) {
        new BukkitRunnable() {
            @Override
            public void run() {
                Firework f = loc.getWorld().spawn(loc, Firework.class);
                FireworkMeta m = f.getFireworkMeta();
                m.addEffects(effect.build());
                m.setPower(power);
                f.setFireworkMeta(m);
            }
        }.runTaskLater(plugin, delay);
    }
}
