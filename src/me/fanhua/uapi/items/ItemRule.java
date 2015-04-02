package me.fanhua.uapi.items;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class ItemRule {
	
	private int id;
	private int data;
	
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
	
	public boolean check(ItemStack item) {
		if (item == null) return false;
		if (item.getTypeId() != this.id) return false;
		if (this.data != -1 && item.getDurability() != this.data) return false;
		
		return true;
	}
	
	public ItemRule copy() {
		return new ItemRule(this.id, this.data);
	}
	
}
