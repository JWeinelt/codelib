package de.codeblocksmc.codelib;

import lombok.Getter;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

@Getter
@Deprecated(forRemoval = true)
public class PotionEffectWrapper {
    private final String type;
    private final int duration;
    private final int amplifier;

    public PotionEffectWrapper(String type, int duration, int amplifier) {
        this.type = type;
        this.duration = duration;
        this.amplifier = amplifier;
    }


    public static PotionEffect fromWrapper(PotionEffectWrapper w) {
        return new PotionEffect(PotionEffectType.getByName(w.getType()), w.getDuration() * 20, w.getAmplifier());
    }

    public static PotionEffectWrapper fromBukkit(PotionEffect e) {
        return new PotionEffectWrapper(e.getType().getKey().getKey(), e.getDuration() / 20, e.getAmplifier());
    }
}
