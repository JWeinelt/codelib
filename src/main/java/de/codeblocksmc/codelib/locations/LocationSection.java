package de.codeblocksmc.codelib.locations;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.function.Consumer;

/**
 * Represents a pair of two {@link LocationWrapper} instances defining the corners of a cuboid area.
 *
 * <p>This class is used to represent rectangular areas in 3D space, defined by two opposite corners.
 * It supports both {@link Location} and {@link LocationWrapper} as input for constructing the area.</p>
 *
 * <p>Common use cases include defining regions for events, boundaries for gameplay mechanics,
 * or areas for structure placement.</p>
 */
@Getter
public class LocationSection {

    /**
     * The first corner of the area.
     */
    private final LocationWrapper l1;

    /**
     * The second corner of the area.
     */
    private final LocationWrapper l2;

    /**
     * Constructs a {@link LocationSection} using two {@link Location} objects.
     *
     * <p>The {@link Location} objects are automatically converted to {@link LocationWrapper} instances.</p>
     *
     * @param l1 The first corner as a {@link Location}.
     * @param l2 The second corner as a {@link Location}.
     */
    public LocationSection(Location l1, Location l2) {
        if (!l1.getWorld().getUID().equals(l2.getWorld().getUID())) throw new IllegalArgumentException("Locations must be in the same world!");
        this.l1 = LocUtil.fromBukkit(l1);
        this.l2 = LocUtil.fromBukkit(l2);
    }

    /**
     * Constructs a {@link LocationSection} using two {@link LocationWrapper} objects.
     *
     * <p>This constructor is useful for directly passing pre-wrapped locations.</p>
     *
     * @param l1 The first corner as a {@link LocationWrapper}.
     * @param l2 The second corner as a {@link LocationWrapper}.
     */
    public LocationSection(LocationWrapper l1, LocationWrapper l2) {
        if (!l1.getWorld().equals(l2.getWorld())) throw new IllegalArgumentException("Locations must be in the same world!");
        this.l1 = l1;
        this.l2 = l2;
    }

    /**
     * Checks whether the provided {@link LocationWrapper} is inside the area defined by this {@link LocationSection}.
     *
     * @param location The {@link LocationWrapper} to check.
     * @return {@code true} if the location is inside the area; {@code false} otherwise.
     */
    public boolean contains(LocationWrapper location) {
        double minX = Math.min(l1.getX(), l2.getX());
        double minY = Math.min(l1.getY(), l2.getY());
        double minZ = Math.min(l1.getZ(), l2.getZ());
        double maxX = Math.max(l1.getX(), l2.getX());
        double maxY = Math.max(l1.getY(), l2.getY());
        double maxZ = Math.max(l1.getZ(), l2.getZ());

        return location.getX() >= minX && location.getX() <= maxX &&
                location.getY() >= minY && location.getY() <= maxY &&
                location.getZ() >= minZ && location.getZ() <= maxZ;
    }

    /**
     * Returns a random {@link LocationWrapper} inside this {@link LocationSection}.
     *
     * @return A random location within the cuboid area.
     */
    public LocationWrapper getRandomLocation() {
        double minX = Math.min(l1.getX(), l2.getX());
        double minY = Math.min(l1.getY(), l2.getY());
        double minZ = Math.min(l1.getZ(), l2.getZ());

        double maxX = Math.max(l1.getX(), l2.getX());
        double maxY = Math.max(l1.getY(), l2.getY());
        double maxZ = Math.max(l1.getZ(), l2.getZ());

        double x = minX + Math.random() * (maxX - minX);
        double y = minY + Math.random() * (maxY - minY);
        double z = minZ + Math.random() * (maxZ - minZ);

        return new LocationWrapper(l1.getWorld(), x, y, z, 0f, 0f);
    }


    /**
     * Loop through every location in the section.
     * @param action A {@link Consumer} for a {@link Location}
     */
    public void forLocation(Consumer<Location> action) {
        for (double x = l1.getX(); x <= l2.getX(); x++) {
            for (double y = l1.getY(); y <= l2.getY(); y++) {
                for (double z = l1.getZ(); z <= l2.getZ(); z++) {
                    Location l = new Location(Bukkit.getWorld(l1.getWorld()), x, y, z);
                    action.accept(l);
                }
            }
        }
    }

    /**
     * Provides a string representation of this {@link LocationSection}.
     *
     * @return A formatted string containing the details of both corners.
     */
    @Override
    public String toString() {
        return String.format("LocationSection[l1=%s, l2=%s]", l1, l2);
    }

    /**
     * Checks if this {@link LocationSection} is equal to another object.
     *
     * <p>Two {@link LocationSection} instances are considered equal if their corners match,
     * regardless of the order of the corners.</p>
     *
     * @param obj The object to compare with.
     * @return {@code true} if the objects are equal; {@code false} otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        LocationSection other = (LocationSection) obj;

        return (l1.equals(other.l1) && l2.equals(other.l2)) ||
                (l1.equals(other.l2) && l2.equals(other.l1));
    }

    /**
     * Generates a hash code for this {@link LocationSection}.
     *
     * @return A hash code based on the fields of the object.
     */
    @Override
    public int hashCode() {
        return l1.hashCode() + l2.hashCode();
    }
}
