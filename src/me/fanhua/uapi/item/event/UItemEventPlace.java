package me.fanhua.uapi.item.event;

import me.fanhua.uapi.item.UItem;
import me.fanhua.uapi.user.User;

import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

public class UItemEventPlace extends UItemEvent {
	
	private Block old;
	private Block block;
	
	public UItemEventPlace(User user, UItem object, ItemStack item, Block old, Block block) {
		super(user, object, item);
		
		this.old = old;
		this.block = block;
	}
	
	public Block getOldBlock() {
		return this.old;
	}
	
	public Block getPlaceBlock() {
		return this.block;
	}

}
