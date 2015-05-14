package me.fanhua.uapi.item.event;

import me.fanhua.uapi.item.UItem;
import me.fanhua.uapi.user.User;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

public class UItemEventClickBlock extends UItemEventClick {
	
	private Block block;
	
	public UItemEventClickBlock(User user, UItem object, ItemStack item, boolean leftButton, Block block) {
		super(user, object, item, leftButton);
		
		this.block = block;
	}
	
	public boolean isAir() {
		return this.block == null;
	}
	
	public boolean isNotAir() {
		return this.block != null;
	}
	
	public Block getBlock() {
		return this.block;
	}
	
	public Location getLocation() {
		return this.block.getLocation();
	}
	
}
