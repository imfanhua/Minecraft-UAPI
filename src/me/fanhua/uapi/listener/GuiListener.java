package me.fanhua.uapi.listener;

import me.fanhua.uapi.api.API;
import me.fanhua.uapi.gui.Gui;
import me.fanhua.uapi.gui.event.ClickAction;
import me.fanhua.uapi.gui.event.ClickActionDouble;
import me.fanhua.uapi.gui.event.ClickActionDrop;
import me.fanhua.uapi.gui.event.ClickActionHotbar;
import me.fanhua.uapi.gui.event.ClickActionMouse;
import me.fanhua.uapi.gui.event.ClickActionUnknown;
import me.fanhua.uapi.gui.type.GuiType;
import me.fanhua.uapi.user.User;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class GuiListener implements Listener {
	
	private static ClickAction newClickAction(InventoryClickEvent event) {
		ClickType type = event.getClick();
		
		if (type == ClickType.LEFT) return new ClickActionMouse(ClickActionMouse.LEFT, false);
		else if (type == ClickType.RIGHT) return new ClickActionMouse(ClickActionMouse.RIGHT, false);
		else if (type == ClickType.MIDDLE) return new ClickActionMouse(ClickActionMouse.MIDDLE, false);
		else if (type == ClickType.SHIFT_LEFT) return new ClickActionMouse(ClickActionMouse.LEFT, true);
		else if (type == ClickType.SHIFT_RIGHT) return new ClickActionMouse(ClickActionMouse.RIGHT, true);
		else if (type == ClickType.DROP) return new ClickActionDrop(false);
		else if (type == ClickType.CONTROL_DROP) return new ClickActionDrop(true);
		else if (type == ClickType.NUMBER_KEY) return new ClickActionHotbar(event.getHotbarButton());
		else if (type == ClickType.DOUBLE_CLICK) return new ClickActionDouble();
		
		return ClickActionUnknown.UNKNOWN;
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onInventoryClickEvent(InventoryClickEvent event) {
		if (event.isCancelled()) return;
		User user = API.to((Player) event.getWhoClicked());
		if (user == null) return;
		Gui gui = user.get(API.USER.GUI).getGui();
		if (gui == null) return;
		event.setCancelled(true);
		
		GuiType type = gui.getType();
		int width = type.getWidth();
		int size = width * type.getHeight();
		
		int slot = event.getRawSlot();
		if (slot == -999) return;
		boolean isClickGui = slot < size;
		
		if (isClickGui) {
			int y = slot / width;
			int x = slot - y * width;
			
			gui.onGuiClicked(x, y, GuiListener.newClickAction(event));
		} else gui.onInventoryClicked(event.getSlot(), GuiListener.newClickAction(event));
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onInventoryCloseEvent(InventoryCloseEvent event) {
		User user = API.to((Player) event.getPlayer());
		if (user == null) return;
		user.get(API.USER.GUI).close(true);
	}
	
}
