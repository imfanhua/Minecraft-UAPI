package me.fanhua.uapi.gui.type;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

import me.fanhua.uapi.gui.render.RenderObject;

public interface GuiType {
	
	public int getWidth();
	public int getHeight();
	
	public RenderObject newRender();
	
	public void initInventory(InventoryHolder holder);
	public Inventory getInventory();
	
}
