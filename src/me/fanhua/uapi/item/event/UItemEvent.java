package me.fanhua.uapi.item.event;

import me.fanhua.uapi.event.base.event.UEventCancelable;
import me.fanhua.uapi.item.UItem;
import me.fanhua.uapi.user.User;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public abstract class UItemEvent extends UEventCancelable {
	
	private User user;
	private UItem object;
	private ItemStack item;
	
	public UItemEvent(User user, UItem object, ItemStack item) {
		this.user = user;
		this.object = object;
		this.item = item;
	}
	
	public User getUser() {
		return this.user;
	}
	
	public UItem getItemObject() {
		return this.object;
	}
	
	public ItemStack getItem() {
		return this.item;
	}
	
	public void setItem(ItemStack item) {
		this.item = item;
	}
	
	public boolean use() {
		if (this.item == null) return false;
		if (this.user.isGameMode(GameMode.CREATIVE)) return true;
		int amount = this.item.getAmount() - 1;
		if (amount < 0) return false;
		if (amount < 1) this.item = null;
		else this.item.setAmount(amount);
		return true;
	}
	
	public Player getPlayer() {
		return this.user.getPlayer(); 
	}
	
}
