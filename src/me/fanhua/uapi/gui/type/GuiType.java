package me.fanhua.uapi.gui.type;

import org.bukkit.inventory.Inventory;

import me.fanhua.uapi.gui.render.Render;

public interface GuiType {
	
	public Render newRender();
	
	public Inventory getInventory();
	
}
