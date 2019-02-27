package com.juubes.wallmagic;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.util.Vector;

public class GeneratorBlock {
	private final Location loc;
	private final Material type;
	private final byte data;
	private final int tickModus;

	private final int SPEED;

	public GeneratorBlock(final int speed, Location loc, Material type, byte data) {
		this.loc = loc.clone();
		this.type = type;
		this.data = data;
		this.SPEED = speed;
		this.tickModus = (int) (Math.random() * SPEED);
		this.loc.subtract(new Vector(0, 1, 0));
	}

	public byte getData() {
		return data;
	}

	public Location getLocation() {
		return loc;
	}

	public Material getType() {
		return type;
	}

	public boolean generate(int tick) {
		if (tick % SPEED != tickModus)
			return true;
		Material mat = loc.getBlock().getType();
		switch (mat) {

		// All the blocks to overflow on
		case AIR:
		case LONG_GRASS:
		case DOUBLE_PLANT:
		case WATER:
		case FLOWER_POT:
		case YELLOW_FLOWER:
		case STATIONARY_LAVA:
		case STATIONARY_WATER:
		case TORCH:
		case REDSTONE_TORCH_OFF:
		case REDSTONE_TORCH_ON:
			break;
		default:
			return false;
		}

		loc.getBlock().setType(type);
		loc.getBlock().setData(data);
		loc.subtract(new Vector(0, 1, 0));
		return true;
	}
}
