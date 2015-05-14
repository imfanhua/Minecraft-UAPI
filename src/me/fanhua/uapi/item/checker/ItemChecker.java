package me.fanhua.uapi.item.checker;

import me.fanhua.uapi.nbt.NBTTagCompound;
import me.fanhua.uapi.utils.ItemUtils;

import org.bukkit.inventory.ItemStack;

public class ItemChecker implements IItemChecker {
	
	private IItemChecker checker;
	private NBTTagCompound nbt;
	
	public ItemChecker(ItemStack item) {
		this(new ItemRule(item), ItemUtils.getNBT(item));
	}
	
	public ItemChecker(IItemChecker checker, NBTTagCompound nbt) {
		this.checker = checker.copy();
		this.nbt = nbt.clone();
	}
	
	public IItemChecker getChecker() {
		return this.checker;
	}
	
	public void setChecker(IItemChecker checker) {
		this.checker = checker.copy();
	}
	
	public NBTTagCompound getNBT() {
		return this.nbt.clone();
	}
	
	public void setNBT(NBTTagCompound nbt) {
		this.nbt = nbt.clone();
	}
	
	@Override
	public boolean check(ItemStack item) {
		if (!this.checker.check(item)) return false;
		return this.nbt.equals(ItemUtils.getNBT(item));
	}
	
	public ItemChecker clone() {
		return new ItemChecker(this.checker, this.nbt);
	}
	
	@Override
	public IItemChecker copy() {
		return this.clone();
	}
	
}
