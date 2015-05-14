package me.fanhua.uapi.item.event;

import me.fanhua.uapi.item.UItem;
import me.fanhua.uapi.user.User;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;

public class UItemEventClickEntity extends UItemEventClick {
	
	private Entity entity;
	
	public UItemEventClickEntity(User user, UItem object, ItemStack item, boolean leftButton, Entity entity) {
		super(user, object, item, leftButton);
		
		this.entity = entity;
	}
	
	public Entity getEntity() {
		return this.entity;
	}
	
	public Location getLocation() {
		return this.entity.getLocation();
	}
	
}
