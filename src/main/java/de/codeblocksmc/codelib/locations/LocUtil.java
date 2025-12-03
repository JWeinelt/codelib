package de.codeblocksmc.codelib.locations;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * Utility class for converting {@link LocationWrapper} and {@link Location} or wrapping {@link LocationSection}
 */
public class LocUtil {

    /**
     * Converts a {@link LocationWrapper} into Bukkit's {@link Location}.
     *
     * <p>This method takes a {@link LocationWrapper}, which is a safe-use representation
     * of a location, and converts it into a {@link Location} object compatible with Bukkit's API.</p>
     *
     * @param w the {@link LocationWrapper} to convert. Must not be null.
     * @return a {@link Location} object representing the same position as the input {@link LocationWrapper}.
     *
     * <p><b>Example:</b></p>
     * <pre>{@code
     * LocationWrapper wrapper = new LocationWrapper("world", 100, 65, 200, 0, 0);
     * Location location = fromWrapper(wrapper);
     * }</pre>
     */
    @NotNull
    public static Location fromWrapper(LocationWrapper w) {
        return new Location(Bukkit.getWorld(w.getWorld()),
                w.getX(), w.getY(), w.getZ(), w.getYaw(), w.getPitch());
    }


    /**
     * Converts Bukkit's {@link Location} into a safe-use {@link LocationWrapper}.
     *
     * <p>This method takes a {@link Location} object and converts it into a {@link LocationWrapper},
     * which can be used safely without requiring a direct reference to a loaded {@link org.bukkit.World} object.</p>
     *
     * @param l the {@link Location} to convert. If the world of the location is null, the method returns null.
     * @return a {@link LocationWrapper} representing the same position as the input {@link Location},
     *         or {@code null} if the location's world is {@code null}.
     *
     * <p><b>Example:</b></p>
     * <pre>{@code
     * Location location = new Location(Bukkit.getWorld("world"), 100, 65, 200, 0, 0);
     * LocationWrapper wrapper = fromBukkit(location);
     * }</pre>
     */
    @Nullable
    public static LocationWrapper fromBukkit(Location l) {
        if (l.getWorld() == null) return null;
        return new LocationWrapper(l.getWorld().getName(), l.getX(), l.getY(), l.getZ(), l.getYaw(), l.getPitch());
    }


    /**
     * Converts a reduced location wrapper ({@link LocationWrapperReduced} to Bukkit's {@link Location} using the given world.
     * <p><b>Note: </b>A reduced location wrapper does NOT contain a field for the world. It must be given separately to create
     * a Location object from it.</p>
     * It is mainly used for dynamic map systems where we won't need to store world data.
     * @param reduced A {@link LocationWrapperReduced} object to convert
     * @param world The name of a {@link World}, the world must exist and be loaded.
     * @return A {@link Location} object
     */
    @NotNull
    public static Location fromWrapperReduced(@NotNull LocationWrapperReduced reduced, String world) {
        World w = Bukkit.getWorld(world);
        if (w == null) throw new IllegalArgumentException("World " + world + " not found");
        return new Location(
                w,
                reduced.getX(), reduced.getY(), reduced.getZ(),
                reduced.getYaw(), reduced.getPitch()
        );
    }



    /**
     * Checks if a {@link Location} is inside a cuboidal area defined by two corner {@link Location}s.
     *
     * <p>The method determines whether the provided {@code playerLocation} is within the bounds of
     * the cuboid defined by {@code loc1} and {@code loc2}, including edges.</p>
     *
     * @param playerLocation the {@link Location} to check.
     * @param loc1 the first corner of the cuboid.
     * @param loc2 the second corner of the cuboid.
     * @return {@code true} if {@code playerLocation} is inside the area, otherwise {@code false}.
     *
     * <p><b>Example:</b></p>
     * <pre>{@code
     * Location loc1 = new Location(Bukkit.getWorld("world"), 100, 64, 100);
     * Location loc2 = new Location(Bukkit.getWorld("world"), 200, 70, 200);
     * Location playerLocation = new Location(Bukkit.getWorld("world"), 150, 65, 150);
     * boolean isInside = isInArea(playerLocation, loc1, loc2);
     * }</pre>
     */
    public static boolean isInArea(Location playerLocation, Location loc1, Location loc2) {
        double minX = Math.min(loc1.getX(), loc2.getX());
        double minY = Math.min(loc1.getY(), loc2.getY());
        double minZ = Math.min(loc1.getZ(), loc2.getZ());
        double maxX = Math.max(loc1.getX(), loc2.getX());
        double maxY = Math.max(loc1.getY(), loc2.getY());
        double maxZ = Math.max(loc1.getZ(), loc2.getZ());

        return playerLocation.getX() >= minX && playerLocation.getX() <= maxX &&
                playerLocation.getY() >= minY && playerLocation.getY() <= maxY &&
                playerLocation.getZ() >= minZ && playerLocation.getZ() <= maxZ;
    }



    /**
     * Checks if a {@link Location} is inside an area defined by a {@link LocationSection}.
     *
     * <p>This method determines whether the given {@code playerLocation} lies within the cuboidal area
     * defined by the two corners stored in the {@link LocationSection}.</p>
     *
     * @param playerLocation the {@link Location} to check.
     * @param section the {@link LocationSection} that defines the area.
     * @return {@code true} if {@code playerLocation} is inside the area, otherwise {@code false}.
     *
     * <p><b>Example:</b></p>
     * <pre>{@code
     * LocationWrapper corner1 = new LocationWrapper("world", 100, 64, 100, 0, 0);
     * LocationWrapper corner2 = new LocationWrapper("world", 200, 70, 200, 0, 0);
     * LocationSection section = new LocationSection(corner1, corner2);
     * Location playerLocation = new Location(Bukkit.getWorld("world"), 150, 65, 150);
     * boolean isInside = isInArea(playerLocation, section);
     * }</pre>
     */
    public static boolean isInArea(Location playerLocation, LocationSection section) {
        Location loc1 = fromWrapper(section.getL1());
        Location loc2 = fromWrapper(section.getL2());
        return isInArea(playerLocation, loc1, loc2);
    }


    /**
     * Checks if a {@link Location} is within a specified radius of another {@link Location}.
     *
     * <p>This method calculates the distance between {@code targetLoc} and {@code middleLoc}
     * and determines if it is less than or equal to the given {@code radius}.</p>
     *
     * @param targetLoc the {@link Location} to check.
     * @param middleLoc the center point of the circle.
     * @param radius the radius to check within.
     * @return {@code true} if {@code targetLoc} is within the radius of {@code middleLoc}, otherwise {@code false}.
     *
     * <p><b>Example:</b></p>
     * <pre>{@code
     * Location middle = new Location(Bukkit.getWorld("world"), 100, 64, 100);
     * Location target = new Location(Bukkit.getWorld("world"), 105, 64, 105);
     * boolean isNear = isNear(target, middle, 10);
     * }</pre>
     */
    public static boolean isNear(Location targetLoc, Location middleLoc, int radius) {
        if (!targetLoc.getWorld().equals(middleLoc.getWorld())) {
            return false;
        }

        double dx = targetLoc.getX() - middleLoc.getX();
        double dy = targetLoc.getY() - middleLoc.getY();
        double dz = targetLoc.getZ() - middleLoc.getZ();

        double distanceSquared = dx * dx + dy * dy + dz * dz;

        return distanceSquared <= (radius * radius);
    }

    public static LocationWrapper getMiddle(LocationSection section) {
        String world = section.getL1().getWorld();  // Assuming both locations are in the same world
        double middleX = (section.getL1().getX() + section.getL2().getX()) / 2;
        double middleY = (section.getL1().getY() + section.getL2().getY()) / 2;
        double middleZ = (section.getL1().getZ() + section.getL2().getZ()) / 2;
        float middleYaw = (section.getL1().getYaw() + section.getL2().getYaw()) / 2;
        float middlePitch = (section.getL1().getPitch() + section.getL2().getPitch()) / 2;

        return new LocationWrapper(world, middleX, middleY, middleZ, middleYaw, middlePitch);
    }

    public static List<Material> getBlocksAround(Location location) {
        List<Material> blocks = new ArrayList<>();
        Location l = new Location(location.getWorld(), location.getX(), location.getY(), location.getZ());

        blocks.add(location.clone().add(0, 0, 1).getBlock().getType());
        location = l;
        blocks.add(location.clone().add(1, 0, 1).getBlock().getType());
        blocks.add(location.clone().add(-1, 0, 1).getBlock().getType());
        blocks.add(location.clone().add(0, 0, -1).getBlock().getType());
        blocks.add(location.clone().add(1, 0, -1).getBlock().getType());
        blocks.add(location.clone().add(-1, 0, -1).getBlock().getType());
        blocks.add(location.clone().add(1, 0, 0).getBlock().getType());
        blocks.add(location.clone().add(-1, 0, 0).getBlock().getType());

        return blocks;
    }
}