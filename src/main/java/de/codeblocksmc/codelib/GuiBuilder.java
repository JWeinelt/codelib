package de.codeblocksmc.codelib;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;

/**
 * Utility class for creating GUIs.
 * Provides flexibility for different inventory types and layouts.
 * @author JustCody
 * @version 1.1
 */
@Deprecated(forRemoval = true)
public class GuiBuilder {

    private final Inventory inv;

    /**
     * Creates a chest GUI with the specified title and rows.
     *
     * @param title Title of the inventory as a string.
     * @param rows Number of rows (1-6, each row contains 9 slots).
     * @throws IllegalArgumentException If rows are less than 1 or greater than 6.
     */
    public GuiBuilder(String title, int rows) {
        if (rows < 1 || rows > 6) {
            throw new IllegalArgumentException("Rows must be between 1 and 6.");
        }
        this.inv = Bukkit.createInventory(null, rows * 9, Component.text(title));
    }

    /**
     * Creates a chest GUI with the specified title and rows.
     *
     * @param title Title of the inventory as a {@link Component}.
     * @param rows Number of rows (1-6, each row contains 9 slots).
     * @throws IllegalArgumentException If rows are less than 1 or greater than 6.
     */
    public GuiBuilder(Component title, int rows) {
        if (rows < 1 || rows > 6) {
            throw new IllegalArgumentException("Rows must be between 1 and 6.");
        }
        this.inv = Bukkit.createInventory(null, rows * 9, title);
    }

    /**
     * Creates a GUI with the specified {@link InventoryType} and title.
     *
     * @param type The inventory type.
     * @param title The title of the inventory as a {@link String}.
     * @throws IllegalArgumentException If the inventory type cannot be viewed.
     */
    public GuiBuilder(InventoryType type, String title) {
        if (type == null) {
            throw new IllegalArgumentException("Inventory type cannot be null.");
        }
        this.inv = Bukkit.createInventory(null, type, Component.text(title));
    }

    /**
     * Creates a GUI with the specified {@link InventoryType} and title.
     *
     * @param type The inventory type.
     * @param title The title of the inventory as a {@link Component}.
     * @throws IllegalArgumentException If the inventory type cannot be viewed.
     */
    public GuiBuilder(InventoryType type, Component title) {
        if (type == null) {
            throw new IllegalArgumentException("Inventory type cannot be null.");
        }
        this.inv = Bukkit.createInventory(null, type, title);
    }

    /**
     * Sets an item in the specified slot.
     *
     * @param slot Slot index (0-based).
     * @param stack The {@link ItemStack} to set.
     * @throws IllegalArgumentException If the slot is out of bounds.
     */
    public GuiBuilder slot(int slot, ItemStack stack) {
        validateSlot(slot);
        inv.setItem(slot, stack);
        return this;
    }

    /**
     * Sets an item in the specified slot using an {@link ItemBuilder}.
     *
     * @param slot Slot index (0-based).
     * @param item The {@link ItemBuilder} instance.
     * @throws IllegalArgumentException If the slot is out of bounds.
     */
    public GuiBuilder slot(int slot, ItemBuilder item) {
        validateSlot(slot);
        inv.setItem(slot, item.build());
        return this;
    }

    /**
     * Fills the specified slots with an item of the given {@link Material}.
     *
     * @param material The material to use.
     * @param slots The slots to fill.
     * @throws IllegalArgumentException If any slot is out of bounds.
     */
    public GuiBuilder slots(Material material, int... slots) {
        ItemStack filler = new ItemBuilder(material).displayname(" ").build();
        for (int slot : slots) {
            validateSlot(slot);
            inv.setItem(slot, filler);
        }
        return this;
    }

    /**
     * Fills the entire inventory with the specified material as a placeholder.
     *
     * @param material The placeholder material.
     */
    public GuiBuilder fillerPlaceholder(Material material) {
        ItemStack filler = new ItemBuilder(material).displayname(" ").build();
        for (int i = 0; i < inv.getSize(); i++) {
            inv.setItem(i, filler);
        }
        return this;
    }

    /**
     * Opens the GUI for the specified player.
     *
     * @param player The player to open the inventory for.
     * @return The built inventory.
     */
    public Inventory openForPlayer(Player player) {
        player.openInventory(inv);
        return inv;
    }

    /**
     * Builds and returns the inventory.
     *
     * @return The built {@link Inventory}.
     */
    public Inventory build() {
        return inv;
    }

    /**
     * Validates a slot index.
     *
     * @param slot The slot index to validate.
     * @throws IllegalArgumentException If the slot is out of bounds.
     */
    private void validateSlot(int slot) {
        if (slot < 0 || slot >= inv.getSize()) {
            throw new IllegalArgumentException("Slot " + slot + " is out of bounds (0-" + (inv.getSize() - 1) + ").");
        }
    }
}
