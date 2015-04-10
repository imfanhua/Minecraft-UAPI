package me.fanhua.uapi.gui.type;

import me.fanhua.uapi.gui.render.RenderDispenser;
import me.fanhua.uapi.gui.render.RenderObject;

import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public class GuiTypeDispenser implements GuiType {
	
	private String title;
	
	private Inventory inventory;
	
	public GuiTypeDispenser(String title) {
		this.title = title;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	@Override
	public void initInventory(InventoryHolder holder) {
		this.inventory = Bukkit.createInventory(holder, InventoryType.DISPENSER, this.title);
	}
	
	@Override
	public RenderObject newRender() {
		return new RenderDispenser(this);
	}
	
	@Override
	public Inventory getInventory() {
		return this.inventory;
	}
	
	@Override
	public int getWidth() {
		return 3;
	}
	
	@Override
	public int getHeight() {
		return 3;
	}
	
}
