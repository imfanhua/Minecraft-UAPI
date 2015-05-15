package me.fanhua.uapi.item.event;

import me.fanhua.uapi.item.UItem;
import me.fanhua.uapi.user.User;

import org.bukkit.inventory.ItemStack;

public class UItemEventClick extends UItemEvent {
	
	private boolean leftButton;
	
	public UItemEventClick(User user, UItem object, ItemStack item, boolean leftButton) {
		super(user, object, item);
		
		this.leftButton = leftButton;
	}
	
	public boolean isLeftButton() {
		return this.leftButton;
	}
	
	public boolean isRightButton() {
		return !this.leftButton;
	}
	
}
