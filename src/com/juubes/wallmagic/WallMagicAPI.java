package com.juubes.wallmagic;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.juubes.api.ItemStackBuilder;

public class WallMagicAPI {
    public static void giveGeneratorItem(Player p, Material mat, int amount, byte data) {
	ItemStack item = new ItemStackBuilder(mat, amount, data)
		.setLore("§7#FG462", "", "§7Generoi seinän valitusta palikasta", "§7    taivaasta lattiaan.")
		.setDisplayName("§eTaikaseinä").enchant(Enchantment.DURABILITY, 1).hideEnchantments(true).build();
	if (p.getInventory().firstEmpty() == -1) {
	    p.getWorld().dropItem(p.getLocation(), item);
	} else {
	    p.getInventory().addItem(item);
	}
    }
}
