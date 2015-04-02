package me.fanhua.uapi.gui.type;

import me.fanhua.uapi.gui.render.Render;

import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

public class GuiTypeChest implements GuiType {
	
	private String title;
	
	private Inventory inventory;
	
	public GuiTypeChest(String title) {
		this.title = title;
		
		this.inventory = Bukkit.createInventory(null, InventoryType.DROPPER, title);
	}
	
	public String getTitle() {
		return this.title;
	}
	
	@Override
	public Render newRender() {
		return null;
	}
	
	@Override
	public Inventory getInventory() {
		return this.inventory;
	}
	
}
