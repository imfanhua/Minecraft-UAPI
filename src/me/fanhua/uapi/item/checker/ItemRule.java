package me.fanhua.uapi.item.checker;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class ItemRule implements IItemChecker {
	
	private int id;
	private int data;
	
	public ItemRule(ItemStack item) {
		this(item.getTypeId(), item.getDurability());
	}
	
	public ItemRule(Material type) {
		this(type, -1);
	}
	
	public ItemRule(Material type, int data) {
		this(type.getId(), data);
	}
	
	public ItemRule(int id) {
		this(id, -1);
	}
	
	public ItemRule(int id, int data) {
		this.setRule(id, data);
	}
	
	public int getType() {
		return this.id;
	}
	
	public int getData() {
		return this.data;
	}
	
	public void setRule(int id, int data) {
		this.id = id;
		this.data = data;
	}
	
	@Override
	public boolean check(ItemStack item) {
		if (item == null) return false;
		if (item.getTypeId() != this.id) return false;
		if (this.data != -1 && item.getDurability() != this.data) return false;
		
		return true;
	}
	
	public ItemRule clone() {
		return new ItemRule(this.id, this.data);
	}
	
	@Override
	public IItemChecker copy() {
		return this.clone();
	}
	
}
