package me.fanhua.uapi.gui.render;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public abstract class RenderObject extends Render {

	public RenderObject(Inventory inventory) {
		super(inventory);
	}
	
	public void draw() {
		ItemStack[] data = this.getData();
		Inventory inventory = this.getInventory();
		inventory.setContents(data);
		this.clearData();
	}

}
