package de.codeblocksmc.codelib.api.wrapping;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
public class ItemWrapper {
    private int amount;
    private Material material;
    private String displayName;
    private List<String> lore = new ArrayList<>();
    private List<EnchantmentWrapper> enchantments = new ArrayList<>();

    public static ItemStack fromWrapper(ItemWrapper w) {
        ItemBuilder b = new ItemBuilder(w.getMaterial());
        b.displayname(w.getDisplayName());
        w.getEnchantments().forEach(ew->{
            b.enchant(Enchantment.getByName(ew.getType().toString()), ew.getLevel());
        });
        b.lore(w.getLore());
        b.amount(w.getAmount());
        return b.build();
    }

    public static ItemWrapper fromBuilder(ItemBuilder b) {
        return fromStack(b.build());
    }

    public static ItemWrapper fromStack(ItemStack stack) {
        ItemWrapper w = new ItemWrapper();
        w.setAmount(stack.getAmount());
        w.setDisplayName(stack.getItemMeta().getDisplayName());
        w.setMaterial(stack.getType());
        w.setLore(stack.getItemMeta().getLore());
        stack.getEnchantments().keySet().forEach(e -> {
            w.getEnchantments().add(new EnchantmentWrapper(EnchantmentWrapper.EnchantmentType.valueOf(e.getName()), stack.getEnchantmentLevel(e)));
        });
        return w;
    }
}
