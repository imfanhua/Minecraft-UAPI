package me.fanhua.uapi.gui.type;

import me.fanhua.uapi.gui.render.RenderHopper;
import me.fanhua.uapi.gui.render.RenderObject;

import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public class GuiTypeHopper implements GuiType {
	
	private String title;
	
	private Inventory inventory;
	
	public GuiTypeHopper(String title) {
		this.title = title;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	@Override
	public void initInventory(InventoryHolder holder) {
		this.inventory = Bukkit.createInventory(holder, InventoryType.HOPPER, this.title);
	}
	
	@Override
	public RenderObject newRender() {
		return new RenderHopper(this);
	}
	
	@Override
	public Inventory getInventory() {
		return this.inventory;
	}
	
	@Override
	public int getWidth() {
		return 5;
	}
	
	@Override
	public int getHeight() {
		return 1;
	}
	
}
