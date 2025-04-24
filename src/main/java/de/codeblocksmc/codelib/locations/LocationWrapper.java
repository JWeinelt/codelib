package de.codeblocksmc.codelib.locations;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

/**
 * A wrapper class for representing and saving positions in JSON files.
 *
 * <p>This class is designed to store positional data in a safe and
 * serialization-friendly format. It is often used in scenarios where
 * a {@link org.bukkit.Location} cannot be directly serialized, such as
 * saving data to configuration files or databases.</p>
 *
 * <p>The class provides getters and setters for all fields and supports
 * JSON serialization through libraries like GSON.</p>
 */
@Getter
@Setter
@NoArgsConstructor
public class LocationWrapper {

    /**
     * The name of the world where the location exists.
     */
    private String world;

    /**
     * The X-coordinate of the location.
     */
    private double x;

    /**
     * The Y-coordinate of the location.
     */
    private double y;

    /**
     * The Z-coordinate of the location.
     */
    private double z;

    /**
     * The yaw (rotation on the Y-axis) of the location.
     */
    private float yaw;

    /**
     * The pitch (rotation on the X-axis) of the location.
     */
    private float pitch;

    /**
     * Constructs a {@link LocationWrapper} with the specified values.
     *
     * @param world the name of the {@link org.bukkit.World} where the location exists.
     * @param x the X-coordinate of the location.
     * @param y the Y-coordinate of the location.
     * @param z the Z-coordinate of the location.
     * @param yaw the yaw (rotation on the Y-axis) of the location.
     * @param pitch the pitch (rotation on the X-axis) of the location.
     */
    public LocationWrapper(String world, double x, double y, double z, float yaw, float pitch) {
        this.world = world;
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
    }

    /**
     * Checks if this {@link LocationWrapper} is valid.
     *
     * <p>A valid {@link LocationWrapper} must have a non-null, non-empty world name.</p>
     *
     * @return {@code true} if the {@link LocationWrapper} is valid; {@code false} otherwise.
     */
    public boolean isValid() {
        return world != null && !world.isEmpty();
    }

    /**
     * Provides a string representation of the {@link LocationWrapper}.
     *
     * @return a formatted string containing the world name, coordinates, yaw, and pitch.
     */
    @Override
    public String toString() {
        return String.format("LocationWrapper[world=%s, x=%.2f, y=%.2f, z=%.2f, yaw=%.2f, pitch=%.2f]",
                world, x, y, z, yaw, pitch);
    }

    /**
     * Checks if this {@link LocationWrapper} is equal to another object.
     *
     * <p>Two {@link LocationWrapper} instances are considered equal if all their fields match.</p>
     *
     * @param obj the object to compare with this instance.
     * @return {@code true} if the objects are equal; {@code false} otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        LocationWrapper other = (LocationWrapper) obj;
        return Double.compare(other.x, x) == 0 &&
                Double.compare(other.y, y) == 0 &&
                Double.compare(other.z, z) == 0 &&
                Float.compare(other.yaw, yaw) == 0 &&
                Float.compare(other.pitch, pitch) == 0 &&
                (world != null ? world.equals(other.world) : other.world == null);
    }

    /**
     * Generates a hash code for the {@link LocationWrapper}.
     *
     * @return a hash code based on the fields of the object.
     */
    @Override
    public int hashCode() {
        int result = (world != null ? world.hashCode() : 0);
        long temp;
        temp = Double.doubleToLongBits(x);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(y);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(z);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + Float.floatToIntBits(yaw);
        result = 31 * result + Float.floatToIntBits(pitch);
        return result;
    }
}
