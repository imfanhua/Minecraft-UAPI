package me.fanhua.uapi.gui.type;

import me.fanhua.uapi.gui.render.Render;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;

public class GuiTypeDropper implements GuiType {
	
	private String title;
	private int height;
	
	private Inventory inventory;
	
	public GuiTypeDropper(String title, int height) {
		this.title = title;
		this.height = height;
		
		this.inventory = Bukkit.createInventory(null, height * 9, title);
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public int getHeight() {
		return this.height;
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
