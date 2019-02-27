package com.juubes.wallmagic;

import java.util.List;

import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class BucketPlaceListener implements Listener {
	private final WallMagic pl;

	public BucketPlaceListener(WallMagic pl) {
		this.pl = pl;
	}

	@EventHandler
	public void onPlace(PlayerInteractEvent e) {
		if (!e.getAction().equals(Action.RIGHT_CLICK_BLOCK))
			return;
		Block b = e.getClickedBlock().getRelative(e.getBlockFace());
		ItemStack item = e.getItem();
		if (item == null)
			return;
		List<String> lore = item.getItemMeta().getLore();
		if (lore == null)
			return;
		if (lore.size() < 1)
			return;
		if (!lore.get(0).contentEquals("§7#FG462"))
			return;

		// TODO: Check if on own land

		GeneratorBlock generator = new GeneratorBlock(pl.getConfig().getInt("SPEEEEEEED"), b.getLocation(),
				item.getType(), item.getData().getData());
		pl.createGenerator(generator);
	}
}
