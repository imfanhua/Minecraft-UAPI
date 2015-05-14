package me.fanhua.uapi.item.checker;

import org.bukkit.inventory.ItemStack;

public interface IItemChecker {
	
	public boolean check(ItemStack item);
	
	public IItemChecker copy();
	
}
