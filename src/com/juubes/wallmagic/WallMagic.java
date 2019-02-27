package com.juubes.wallmagic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class WallMagic extends JavaPlugin implements Runnable {

	@Override
	public void onEnable() {
		saveDefaultConfig();
		final int SPEED = getConfig().getInt("SPEEEEEEED");
		Bukkit.getPluginManager().registerEvents(new BucketPlaceListener(this), this);
		Bukkit.getScheduler().runTaskTimer(this, this, 0, SPEED);

		getCommand("wallmagic").setExecutor((CommandSender sender, Command cmd, String lbl, String[] args) -> {
			if (!sender.isOp()) {
				sender.sendMessage("§cEi permejä");
				return true;
			}
			if (!(sender instanceof Player))
				return true;

			Player p = (Player) sender;
			if (args.length == 0) {
				WallMagicAPI.giveGeneratorItem(p, Material.OBSIDIAN, 64, (byte) 0);
			} else if (args.length == 1) {
				try {
					byte b = Byte.parseByte(args[0]);
					WallMagicAPI.giveGeneratorItem(p, Material.STAINED_CLAY, 64, b);
				} catch (Exception e) {
					sender.sendMessage("§cNyt kyl kusi joku... koita numeroa.");
				}
			} else {
				sender.sendMessage("§c/wallmagic <data>");
			}
			return true;
		});
	}

	@Override
	public void onDisable() {

	}

	private int tick = 0;

	@Override
	public void run() {
		tick++;
		Set<GeneratorBlock> blocksDead = new HashSet<>(3);
		for (GeneratorBlock block : generators) {
			boolean alive = block.generate(tick);
			if (!alive)
				blocksDead.add(block);
		}
		for (GeneratorBlock generatorBlock : blocksDead) {
			generators.remove(generatorBlock);
		}
	}

	private final static List<GeneratorBlock> generators = new ArrayList<>();

	public void createGenerator(GeneratorBlock generator) {
		generators.add(generator);
	}
}
