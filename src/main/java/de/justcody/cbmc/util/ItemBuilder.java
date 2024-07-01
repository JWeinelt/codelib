package de.justcody.cbmc.util;

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
import org.bukkit.inventory.meta.trim.TrimMaterial;
import org.bukkit.inventory.meta.trim.TrimPattern;
import org.bukkit.potion.PotionEffect;

import java.util.Arrays;

/**
 * Utility class for creating Items.
 * @author JustCody
 * @version 1.0
 */

public class ItemBuilder {
    private final ItemStack stack;
    private ItemMeta meta;



    public ItemBuilder(Material material) {
        stack = new ItemStack(material);
        meta = stack.getItemMeta();
    }


    /**
     * Set the display name of the item.
     * @param name Displayname
     */
    public ItemBuilder displayname(String name) {
        meta = stack.getItemMeta();
        meta.setDisplayName(name);
        stack.setItemMeta(meta);
        return this;
    }


    /**
     * Update the lore of the item.
     * @param lore Lores
     */
    public ItemBuilder lore(String... lore) {
        meta = stack.getItemMeta();
        meta.setLore(Arrays.asList(lore));
        stack.setItemMeta(meta);
        return this;
    }


    /**
     * Adds a single {@link org.bukkit.inventory.ItemFlag} to the item
     * @param flag Flag to add
     */
    public ItemBuilder flag(ItemFlag flag) {
        meta = stack.getItemMeta();
        meta.addItemFlags(flag);
        stack.setItemMeta(meta);
        return this;
    }

    /**
     * Adds multiple {@link org.bukkit.inventory.ItemFlag} to the item
     * @param flags Flags to add
     */
    public ItemBuilder flags(ItemFlag... flags) {
        meta = stack.getItemMeta();
        meta.addItemFlags(flags);
        stack.setItemMeta(meta);
        return this;
    }


    /**
     * Adds an {@link org.bukkit.enchantments.Enchantment} to the item.
     * @param enchantment Enchantment
     * @param level Enchantment level. Levels over the restriction are possible.
     */
    public ItemBuilder enchant(Enchantment enchantment, int level) {
        meta = stack.getItemMeta();
        meta.addEnchant(enchantment, level, true);
        stack.setItemMeta(meta);
        return this;
    }


    /**
     * Applies the skin texture of a player to the item.
     * This ONLY works with a player head as Material
     * @param profile Profile of a player
     */
    public ItemBuilder owner(PlayerProfile profile) {
        if (stack.getType().equals(Material.PLAYER_HEAD) || stack.getType().equals(Material.PLAYER_WALL_HEAD)) {
            SkullMeta skullMeta = (SkullMeta) meta;
            skullMeta.setPlayerProfile(profile);
            meta = skullMeta;
            return this;
        }
        return this;
    }


    /**
     * Applies an armor trim to the item (Only works with Armor!)
     * @param trim An instance of {@link org.bukkit.inventory.meta.trim.ArmorTrim}
     */
    public ItemBuilder armorTrim(ArmorTrim trim) {
        ArmorMeta armorMeta = (ArmorMeta) meta;
        armorMeta.setTrim(trim);
        meta = armorMeta;
        return this;
    }


    /**
     * Applies an armor trim to the item (only works with armor!)
     * @param trimMaterial Material {@link org.bukkit.inventory.meta.trim.TrimMaterial} for armor trim
     * @param pattern {@link org.bukkit.inventory.meta.trim.TrimPattern} of the armor trim
     */
    public ItemBuilder armorTrim(TrimMaterial trimMaterial, TrimPattern pattern) {
        ArmorTrim trim = new ArmorTrim(trimMaterial, pattern);
        ArmorMeta armorMeta = (ArmorMeta) meta;
        armorMeta.setTrim(trim);
        meta = armorMeta;
        return this;
    }


    /**
     * Applies a specified color to the item (only leather armor)
     * @param color {@link org.bukkit.Color} of the leather armor
     */
    public ItemBuilder leatherColor(Color color) {
        LeatherArmorMeta leatherArmorMeta = (LeatherArmorMeta) meta;
        leatherArmorMeta.setColor(color);
        meta = leatherArmorMeta;
        return this;
    }

    /**
     * Applies a color to the item (potions only)
     * @param color {@link org.bukkit.Color} of the potion
     */
    public ItemBuilder potionColor(Color color) {
        PotionMeta potionMeta = (PotionMeta) meta;
        potionMeta.setColor(color);
        meta = potionMeta;
        return this;
    }


    /**
     * Adds a {@link org.bukkit.potion.PotionEffect} to the item if it is a potion
     * @param effect {@link org.bukkit.potion.PotionEffect} to add
     */
    public ItemBuilder potionEffect(PotionEffect effect) {
        PotionMeta potionMeta = (PotionMeta) meta;
        potionMeta.addCustomEffect(effect, true);
        meta = potionMeta;
        return this;
    }

    /**
     * Adds multiple {@link org.bukkit.potion.PotionEffect} to the item if it is a potion
     * @param effects list of {@link org.bukkit.potion.PotionEffect} to add
     */
    public ItemBuilder potionEffects(PotionEffect... effects) {
        PotionMeta potionMeta = (PotionMeta) meta;
        for (PotionEffect e : effects)
            potionMeta.addCustomEffect(e, true);
        meta = potionMeta;
        return this;
    }


    /**
     * Adds a {@link org.bukkit.MusicInstrument} to your item only applicable to goat horns.
     * @param instrument {@link org.bukkit.MusicInstrument} to add
     */
    public ItemBuilder musicInstrument(MusicInstrument instrument) {
        MusicInstrumentMeta musicInstrumentMeta = (MusicInstrumentMeta) meta;
        musicInstrumentMeta.setInstrument(instrument);
        meta = musicInstrumentMeta;
        return this;
    }


    /**
     * Sets the title of a book
     * @param title {@link String} as title
     */
    public ItemBuilder bookTitle(String title) {
        BookMeta bookMeta = (BookMeta) meta;
        bookMeta.setTitle(title);
        meta = bookMeta;
        return this;
    }

    /**
     * Sets the author of a book
     * @param author {@link String} as author
     */
    public ItemBuilder bookAuthor(String author) {
        BookMeta bookMeta = (BookMeta) meta;
        bookMeta.setAuthor(author);
        meta = bookMeta;
        return this;
    }

    /**
     * Adds a page to the book.
     * You can add up to 50 pages with up to 265 characters per page.
     * @param page {@link String} page to add
     */
    public ItemBuilder bookPage(String page) {
        BookMeta bookMeta = (BookMeta) meta;
        bookMeta.addPages(Component.text(page));
        meta = bookMeta;
        return this;
    }


    /**
     * Set {@link org.bukkit.inventory.meta.BookMeta.Generation} of the book.
     * Available types:
     *   ORIGINAL,
     *   COPY_OF_ORIGINAL,
     *   COPY_OF_COPY,
     *   TATTERED
     * @param generation {@link org.bukkit.inventory.meta.BookMeta.Generation} of the book
     */
    public ItemBuilder bookGeneration(BookMeta.Generation generation) {
        BookMeta bookMeta = (BookMeta) meta;
        bookMeta.setGeneration(generation);
        meta = bookMeta;
        return this;
    }


    /**
     * Set the type of the axolotl of the item. Only applicable to Axolotl Buckets
     * @param variant {@link org.bukkit.entity.Axolotl.Variant} of the axolotl
     */
    public ItemBuilder axolotl(Axolotl.Variant variant) {
        AxolotlBucketMeta axolotlBucketMeta = (AxolotlBucketMeta) meta;
        axolotlBucketMeta.setVariant(variant);
        meta = axolotlBucketMeta;
        return this;
    }


    /**
     * Builds the whole class to an ItemStack
     * @return stack {@link org.bukkit.inventory.ItemStack} that can be used
     */
    public ItemStack build() {
        return stack;
    }
}
