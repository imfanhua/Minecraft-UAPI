package me.fanhua.uapi.gui.type;

import me.fanhua.uapi.gui.render.RenderChest;
import me.fanhua.uapi.gui.render.RenderObject;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public class GuiTypeChest implements GuiType {
	
	private String title;
	private int height;
	
	private Inventory inventory;
	
	public GuiTypeChest(String title, int height) {
		this.title = title;
		this.height = height;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	@Override
	public void initInventory(InventoryHolder holder) {
		this.inventory = Bukkit.createInventory(holder, this.height * 9, this.title);
	}
	
	@Override
	public RenderObject newRender() {
		return new RenderChest(this);
	}
	
	@Override
	public Inventory getInventory() {
		return this.inventory;
	}

	@Override
	public int getWidth() {
		return 9;
	}
	
	@Override
	public int getHeight() {
		return this.height;
	}
	
}
