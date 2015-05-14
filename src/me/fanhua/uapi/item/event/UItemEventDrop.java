package me.fanhua.uapi.item.event;

import org.bukkit.inventory.ItemStack;

import me.fanhua.uapi.item.UItem;
import me.fanhua.uapi.user.User;

public class UItemEventDrop extends UItemEvent {
	
	public UItemEventDrop(User user, UItem object, ItemStack item) {
		super(user, object, item);
	}
	
	public void remove() {
		this.setItem(null);
	}
	
}
