package de.codeblocksmc.codelib.locations;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

/**
 * @author Try
 * A wrapper class for representing and saving positions in JSON files.
 *
 * <p>This class is designed to store positional data in a safe and
 * serialization-friendly format. It is often used in scenarios where
 * a {@link org.bukkit.Location} cannot be directly serialized, such as
 * saving data to configuration files or databases.</p>
 *
 * <p>The class provides getters and setters for all fields except worlds and supports
 * JSON serialization through libraries like GSON.</p>
 *
 * <p>The {@link LocationWrapperReduced} does not provide a {@link org.bukkit.World} field. It is indented for implementations
 * of games that are not world-dependent, e.g., dynamic map loading.</p>
 *
 * Use {@link LocationWrapperReduced#toWrapper(String)} to convert it to a full {@link LocationWrapper}.
 */
@Getter
@Setter
@NoArgsConstructor
public class LocationWrapperReduced {

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
     * Constructs a {@link LocationWrapperReduced} with the specified values.
     *
     * @param x the X-coordinate of the location.
     * @param y the Y-coordinate of the location.
     * @param z the Z-coordinate of the location.
     * @param yaw the yaw (rotation on the Y-axis) of the location.
     * @param pitch the pitch (rotation on the X-axis) of the location.
     */
    public LocationWrapperReduced(double x, double y, double z, float yaw, float pitch) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
    }


    /**
     * Creates a regular {@link LocationWrapper} object with a world.
     * @param world World the location will have
     * @return {@link LocationWrapper} representing this class with a world.
     */
    public LocationWrapper toWrapper(String world) {
        return new LocationWrapper(world, x, y, z, yaw, pitch);
    }

    /**
     * Provides a string representation of the {@link LocationWrapperReduced}.
     *
     * @return a formatted string containing the world name, coordinates, yaw, and pitch.
     */
    @Override
    public String toString() {
        return String.format("LocationWrapperReduced[ x=%.2f, y=%.2f, z=%.2f, yaw=%.2f, pitch=%.2f]",
               x, y, z, yaw, pitch);
    }

    /**
     * Checks if this {@link LocationWrapperReduced} is equal to another object.
     *
     * <p>Two {@link LocationWrapperReduced} instances are considered equal if all their fields match.</p>
     *
     * @param obj the object to compare with this instance.
     * @return {@code true} if the objects are equal; {@code false} otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null ) return false;
        if (this == obj) return true;

        if(obj instanceof LocationWrapperReduced other){
            return Double.compare(other.x, x) == 0 &&
                    Double.compare(other.y, y) == 0 &&
                    Double.compare(other.z, z) == 0 &&
                    Float.compare(other.yaw, yaw) == 0 &&
                    Float.compare(other.pitch, pitch) == 0;
        }

        return false;

    }

    /**
     * Generates a hash code for the {@link LocationWrapperReduced}.
     *
     * @return a hash code based on the fields of the object.
     */

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z, yaw, pitch);
    }
}
