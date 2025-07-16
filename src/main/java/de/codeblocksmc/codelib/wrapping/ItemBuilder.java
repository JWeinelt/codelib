package de.codeblocksmc.codelib.wrapping;

import com.destroystokyo.paper.profile.PlayerProfile;
import net.kyori.adventure.text.Component;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.MusicInstrument;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Axolotl;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.*;
import org.bukkit.inventory.meta.trim.ArmorTrim;
import org.bukkit.potion.PotionEffect;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * A utility class for creating and modifying {@link ItemStack} instances with a fluent API.
 *
 * <p>This class allows easy configuration of items with custom properties such as display names,
 * lore, enchantments, item flags, and more.</p>
 *
 * @author JustCody
 * @version 1.1
 */
public class ItemBuilder {

    private ItemStack stack;
    private ItemMeta meta;

    /**
     * Constructs an {@link ItemBuilder} with the specified {@link Material}.
     *
     * @param material The {@link Material} for the item.
     *
     * Note: Material with type AIR can be used, but may cause bugs.
     */
    public ItemBuilder(Material material) {
        Objects.requireNonNull(material, "Material cannot be null.");
        if (material == Material.AIR) {
            this.stack = new ItemStack(material);
            return;
        }
        this.stack = new ItemStack(material);
        this.meta = stack.getItemMeta();
    }

    /**
     * Sets the display name of the item.
     *
     * @param name The new display name.
     * @return This builder instance.
     */
    public ItemBuilder displayname(String name) {
        meta.setDisplayName(name);
        return this;
    }

    public ItemBuilder material(Material material) {
        if (material == Material.AIR) throw new IllegalArgumentException("Material cannot be null or AIR.");
        stack = stack.withType(material);
        return this;
    }

    /**
     * Sets the item to be unbreakable.
     *
     * @param unbreakable Whether the item is unbreakable.
     * @return This builder instance.
     */
    public ItemBuilder unbreakable(boolean unbreakable) {
        meta.setUnbreakable(unbreakable);
        return this;
    }

    /**
     * Sets the lore of the item.
     *
     * @param lore The lore lines to set.
     * @return This builder instance.
     */
    public ItemBuilder lore(String... lore) {
        meta.setLore(Arrays.asList(lore));
        return this;
    }

    /**
     * Sets the lore of the item.
     *
     * @param lore The lore lines as a list.
     * @return This builder instance.
     */
    public ItemBuilder lore(List<String> lore) {
        meta.setLore(lore);
        return this;
    }

    /**
     * Sets custom model data for the item.
     *
     * @param data The custom model data.
     * @return This builder instance.
     */
    public ItemBuilder customModelData(int data) {
        meta.setCustomModelData(data);
        return this;
    }

    /**
     * Adds a single {@link ItemFlag} to the item.
     *
     * @param flag The flag to add.
     * @return This builder instance.
     */
    public ItemBuilder flag(ItemFlag flag) {
        meta.addItemFlags(flag);
        return this;
    }

    /**
     * Adds multiple {@link ItemFlag}s to the item.
     *
     * @param flags The flags to add.
     * @return This builder instance.
     */
    public ItemBuilder flags(ItemFlag... flags) {
        meta.addItemFlags(flags);
        return this;
    }

    /**
     * Adds an {@link Enchantment} to the item.
     *
     * @param enchantment The enchantment to add.
     * @param level The level of the enchantment.
     * @return This builder instance.
     */
    public ItemBuilder enchant(Enchantment enchantment, int level) {
        meta.addEnchant(enchantment, level, true);
        return this;
    }

    /**
     * Sets the amount of the item stack.
     *
     * @param amount The new amount.
     * @return This builder instance.
     */
    public ItemBuilder amount(int amount) {
        stack.setAmount(amount);
        return this;
    }

    /**
     * Applies a player profile to the item (for player heads).
     *
     * @param profile The {@link PlayerProfile} to set.
     * @return This builder instance.
     */
    public ItemBuilder owner(PlayerProfile profile) {
        if (meta instanceof SkullMeta skullMeta) {
            skullMeta.setPlayerProfile(profile);
        }
        return this;
    }

    /**
     * Sets an armor trim for the item.
     *
     * @param trim The {@link ArmorTrim} to apply.
     * @return This builder instance.
     */
    public ItemBuilder armorTrim(ArmorTrim trim) {
        if (meta instanceof ArmorMeta armorMeta) {
            armorMeta.setTrim(trim);
        }
        return this;
    }

    /**
     * Sets a leather color for leather armor.
     *
     * @param color The {@link Color} to apply.
     * @return This builder instance.
     */
    public ItemBuilder leatherColor(Color color) {
        if (meta instanceof LeatherArmorMeta leatherArmorMeta) {
            leatherArmorMeta.setColor(color);
        }
        return this;
    }

    /**
     * Sets a color for potions.
     *
     * @param color The {@link Color} to apply.
     * @return This builder instance.
     */
    public ItemBuilder potionColor(Color color) {
        if (meta instanceof PotionMeta potionMeta) {
            potionMeta.setColor(color);
        }
        return this;
    }

    /**
     * Adds a potion effect to the item (if it's a potion).
     *
     * @param effect The {@link PotionEffect} to add.
     * @return This builder instance.
     */
    public ItemBuilder potionEffect(PotionEffect effect) {
        if (meta instanceof PotionMeta potionMeta) {
            potionMeta.addCustomEffect(effect, true);
        }
        return this;
    }

    /**
     * Adds multiple potion effects to the item (if it's a potion).
     *
     * @param effects The potion effects to add.
     * @return This builder instance.
     */
    public ItemBuilder potionEffects(PotionEffect... effects) {
        if (meta instanceof PotionMeta potionMeta) {
            for (PotionEffect effect : effects) {
                potionMeta.addCustomEffect(effect, true);
            }
        }
        return this;
    }

    /**
     * Sets the instrument for a goat horn.
     *
     * @param instrument The {@link MusicInstrument} to set.
     * @return This builder instance.
     */
    public ItemBuilder musicInstrument(MusicInstrument instrument) {
        if (meta instanceof MusicInstrumentMeta musicMeta) {
            musicMeta.setInstrument(instrument);
        }
        return this;
    }

    /**
     * Sets the title of a book.
     *
     * @param title The title to set.
     * @return This builder instance.
     */
    public ItemBuilder bookTitle(String title) {
        if (meta instanceof BookMeta bookMeta) {
            bookMeta.setTitle(title);
        }
        return this;
    }

    /**
     * Sets the author of a book.
     *
     * @param author The author to set.
     * @return This builder instance.
     */
    public ItemBuilder bookAuthor(String author) {
        if (meta instanceof BookMeta bookMeta) {
            bookMeta.setAuthor(author);
        }
        return this;
    }

    /**
     * Adds a page to a book.
     *
     * @param page The content of the page.
     * @return This builder instance.
     */
    public ItemBuilder bookPage(String page) {
        if (meta instanceof BookMeta bookMeta) {
            bookMeta.addPages(Component.text(page));
        }
        return this;
    }

    /**
     * Sets the axolotl variant for axolotl buckets.
     *
     * @param variant The {@link Axolotl.Variant} to set.
     * @return This builder instance.
     */
    public ItemBuilder axolotl(Axolotl.Variant variant) {
        if (meta instanceof AxolotlBucketMeta axolotlMeta) {
            axolotlMeta.setVariant(variant);
        }
        return this;
    }

    /**
     * Builds the item and returns the {@link ItemStack}.
     *
     * @return The built {@link ItemStack}.
     */
    public ItemStack build() {
        stack.setItemMeta(meta);
        return stack;
    }

    public Material getMaterial() {
        return stack.getType();
    }

    public int getAmount() {
        return stack.getAmount();
    }
}
