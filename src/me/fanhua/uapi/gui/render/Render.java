package me.fanhua.uapi.gui.render;

import org.bukkit.inventory.Inventory;

public abstract class Render {
	
	private Inventory inventory;
	
	public Render(Inventory inventory) {
		this.inventory = inventory;
	}
	
	protected Inventory getInventory() {
		return inventory;
	}
	
}
