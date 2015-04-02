package me.fanhua.uapi.blocks;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

public class BlockRule {
	
	private int id;
	private int data;
	
	public BlockRule(Material type) {
		this(type, -1);
	}
	
	public BlockRule(Material type, int data) {
		this(type.getId(), data);
	}
	
	public BlockRule(int id) {
		this(id, -1);
	}
	
	public BlockRule(int id, int data) {
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
	
	public boolean check(Location location) {
		return this.check(location.getBlock());
	}
	
	public boolean check(Block block) {
		if (block == null) return false;
		if (block.getTypeId() != this.id) return false;
		if (this.data != -1 && block.getData() != this.data) return false;
		
		return true;
	}
	
	public BlockRule copy() {
		return new BlockRule(this.id, this.data);
	}
	
}
