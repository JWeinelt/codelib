package de.codeblocksmc.codelib.util;

import com.google.common.util.concurrent.AtomicDouble;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;

/*
 * @author Try
 * @version 1.1
 */

public class ParticleUtil {
    public static void spawnParticleCircle(Location center, double radius, Particle particle, int points) {
        World world = center.getWorld();
        if (world == null) return;

        for (int i = 0; i < points; i++) {
            double angle = 2 * Math.PI * i / points;
            double x = Math.cos(angle) * radius;
            double z = Math.sin(angle) * radius;
            Location particleLoc = center.clone().add(new Vector(x, 0, z));
            world.spawnParticle(particle, particleLoc, 1, 0, 0, 0, 0);
        }
    }

    public static void spawnRotatingCircle(Location center, double radius, Particle particle, int points, int durationTicks, double angularVelocity, Plugin plugin) {
        World world = center.getWorld();
        if (world == null) return;




        final  AtomicDouble angleOffset = new AtomicDouble(0);

        BukkitTask particleTask = Bukkit.getScheduler().runTaskTimer(plugin,()->{

            for (int i = 0; i < points; i++) {
                double angle = 2 * Math.PI * i / points + angleOffset.get();
                double x = Math.cos(angle) * radius;
                double z = Math.sin(angle) * radius;
                Location loc = center.clone().add(new Vector(x, 0, z));
                world.spawnParticle(particle, loc, 1, 0, 0, 0, 0);
            }
            angleOffset.addAndGet(angularVelocity);

        }, 0, 2L);

        Bukkit.getScheduler().runTaskLater(plugin, particleTask::cancel, durationTicks);
    }


    public static void spawnSpiral(Location center, double radius, double height, int turns, Particle particle, int pointsPerTurn, Plugin plugin) {
        World world = center.getWorld();
        if (world == null) return;

        new BukkitRunnable() {
            double y = 0;
            double angle = 0;
            final double yStep = height / (turns * pointsPerTurn);
            final double angleStep = 2 * Math.PI / pointsPerTurn;

            @Override
            public void run() {
                if (y >= height) {
                    cancel();
                    return;
                }

                double x = Math.cos(angle) * radius;
                double z = Math.sin(angle) * radius;
                Location loc = center.clone().add(x, y, z);
                world.spawnParticle(particle, loc, 1, 0, 0, 0, 0);

                angle += angleStep;
                y += yStep;
            }
        }.runTaskTimer(plugin, 0, 1L);
    }

}
