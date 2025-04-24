package de.codeblocksmc.codelib.wrapping;

import lombok.Getter;

@Getter
public class EnchantmentWrapper {
    private final EnchantmentType type;
    private final int level;

    public EnchantmentWrapper(EnchantmentType type, int level) {
        this.type = type;
        this.level = level;
    }

    public enum EnchantmentType {
        PROTECTION_ENVIRONMENTAL,
        PROTECTION_FIRE,
        PROTECTION_FALL,
        PROTECTION_EXPLOSIONS,
        PROTECTION_PROJECTILE,
        OXYGEN,
        WATER_WORKER,
        THORNS,
        DEPTH_STRIDER,
        FROST_WALKER,
        BINDING_CURSE,
        DAMAGE_ALL,
        DAMAGE_UNDEAD,
        DAMAGE_ARTHROPODS,
        KNOCKBACK,
        FIRE_ASPECT,
        LOOT_BONUS_MOBS,
        SWEEPING_EDGE,
        DIG_SPEED,
        SILK_TOUCH,
        DURABILITY,
        LOOT_BONUS_BLOCKS,
        ARROW_DAMAGE,
        ARROW_KNOCKBACK,
        ARROW_FIRE,
        ARROW_INFINITE,
        LUCK,
        LURE,
        LOYALTY,
        IMPALING,
        RIPTIDE,
        CHANNELING,
        MULTISHOT,
        QUICK_CHARGE,
        PIERCING,
        MENDING,
        VANISHING_CURSE,
        SOUL_SPEED,
        SWIFT_SNEAK
    }
}
