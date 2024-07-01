package de.justcody.cbmc.util;


import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

/**
 * Utility class for creating GUIs
 * @author JustCody
 * @version 1.0
 */
public class GuiBuilder {
    private final Inventory inv;

    /**
     * GUI
     * @param title Title of the Inventory
     * @param rows Rows of the chest GUI (NOT slots)
     */
    public GuiBuilder(String title, int rows) {
        inv = Bukkit.createInventory(null, rows*9, Component.text(title));
    }
    /**
     * GUI
     * @param title Title as {@link net.kyori.adventure.text.Component} of the Inventory
     * @param rows Rows of the chest GUI (NOT slots)
     */
    public GuiBuilder(Component title, int rows) {
        inv = Bukkit.createInventory(null, rows*9, title);
    }


    /**
     * Creates an {@link GuiBuilder} with {@link InventoryType} as type.
     * If the type is {@link InventoryType#CHEST} the default size will be 27.
     * Some inventory types may not render the title on Minecraft Client.
     * <br>
     * {@link InventoryType#WORKBENCH} will not process any crafting when created with this method.
     * Use {@link Player#openWorkbench(Location, boolean)} instead.
     * <br>
     * {@link InventoryType#ENCHANTING} will not process any enchantments to {@link ItemStack}s here.
     * Use {@link Player#openEnchanting(Location, boolean)} instead.
     * <br>When using Paper's Adventure API, you may use {@link GuiBuilder#GuiBuilder(InventoryType, Component)} instead
     * @throws IllegalArgumentException - if the {@link InventoryType} cannot be viewed.
     * @param type The {@link InventoryType} to use
     * @param title The title of the inventory
     */
    public GuiBuilder(InventoryType type, String title) throws IllegalArgumentException {
        inv = Bukkit.createInventory(null, type, Component.text(title));
    }

    /**
     * Creates an {@link GuiBuilder} with {@link InventoryType} as type.
     * If the type is {@link InventoryType#CHEST} the default size will be 27.
     * Some inventory types may not render the title on Minecraft Client.
     * <br>
     * {@link InventoryType#WORKBENCH} will not process any crafting when created with this method.
     * Use {@link Player#openWorkbench(Location, boolean)} instead.
     * <br>
     * {@link InventoryType#ENCHANTING} will not process any enchantments to {@link ItemStack}s here.
     * Use {@link Player#openEnchanting(Location, boolean)} instead.
     * @param type The {@link InventoryType} to use
     * @param title The title in form of {@link Component} in favour of Paper of the inventory
     */
    public GuiBuilder(InventoryType type, Component title) {
        inv = Bukkit.createInventory(null, type, title);
    }


    /**
     * Set item in slot.
     * Alternatively an {@link ItemBuilder} can be used.
     * @see GuiBuilder#slot(int, ItemBuilder)
     * @param slot slot to set
     * @param stack {@link ItemStack} to set
     */
    public GuiBuilder slot(int slot, ItemStack stack) {
        inv.setItem(slot, stack);
        return this;
    }

    /**
     * Set item in slot. Uses an {@link de.justcody.cbmc.util.ItemBuilder} to set
     * @param slot slot to set
     * @param item IItemBuilder to use
     */
    public GuiBuilder slot(int slot, ItemBuilder item) {
        inv.setItem(slot, item.build());
        return this;
    }

    /**
     * Set an ItemStack with given material and empty display name in the given slots.
     * @param material {@link org.bukkit.Material} to use
     * @param slots Slots to set
     */
    public GuiBuilder slots(Material material, int... slots) {
        for (int i : slots) inv.setItem(i, new ItemBuilder(material).displayname(" ").build());
        return this;
    }


    /**
     * Open the built {@link org.bukkit.inventory.Inventory} for a player
     * @param player {@link org.bukkit.entity.Player} to use
     * @return Built {@link org.bukkit.inventory.Inventory}
     */
    public Inventory openForPlayer(Player player) {
        player.openInventory(inv);
        return inv;
    }


    /**
     * Builds the {@link org.bukkit.inventory.Inventory}
     * @return {@link org.bukkit.inventory.Inventory}
     */
    public Inventory build() {
        return inv;
    }
}
