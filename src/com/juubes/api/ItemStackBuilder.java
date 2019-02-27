package com.juubes.api;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemStackBuilder {
	private final ItemStack item;

	public ItemStackBuilder(ItemStack item) {
		this.item = item;
	}

	public ItemStackBuilder(Material mat) {
		this.item = new ItemStack(mat);
	}

	public ItemStackBuilder(Material mat, int amount) {
		this.item = new ItemStack(mat, amount);
	}

	public ItemStackBuilder(Material mat, int amount, byte data) {
		this.item = new ItemStack(mat, amount, data);
	}

	public ItemStackBuilder hideEnchantments(boolean b) {
		ItemMeta meta = item.getItemMeta();
		if (b)
			meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		else
			meta.removeItemFlags(ItemFlag.HIDE_ENCHANTS);
		item.setItemMeta(meta);
		return this;
	}

	public ItemStackBuilder enchant(Enchantment enc, int level) {
		item.addUnsafeEnchantment(enc, level);
		return this;
	}

	public ItemStack build() {
		return item;
	}

	public ItemStackBuilder setLore(String... lines) {
		ItemMeta meta = item.getItemMeta();
		meta.setLore(Arrays.asList(lines));
		item.setItemMeta(meta);
		return this;
	}

	public ItemStackBuilder setDisplayName(String name) {
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(name);
		item.setItemMeta(meta);
		return this;
	}
}
