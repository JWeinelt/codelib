package de.codeblocksmc.codelib.locations;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

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
 * <p>The class provides getters and setters for all fields and supports
 * JSON serialization through libraries like GSON.</p>
 */
@Getter
@Setter
@NoArgsConstructor
public class LocationWrapper extends LocationWrapperReduced {

    /**
     * The name of the world where the location exists.
     */
    private String world;

    public LocationWrapper(String world, double x, double y, double z, float yaw, float pitch) {
        super(x,y,z,yaw,pitch);
        this.world = world;
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
                world, getX(), getY(), getZ(), getYaw(), getPitch());
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
        boolean result =  super.equals(obj);

        if (obj instanceof LocationWrapper other)
           return result&& (world != null ? world.equals(other.world) : other.world == null);
        else{
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), world);
    }
}
