package me.fanhua.uapi.blocks;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Location;
import org.bukkit.block.Block;

public class BlocksChecker {
	
	private BlockRule[][][] data;
	
	private int widthX;
	private int widthZ;
	private int height;
	
	private int offsetX;
	private int offsetZ;
	private int offsetY;
	
	private BlockRule centerRule;
	
	public BlocksChecker(String[][] data, Object... args) {
		this.setRule(data, args);
	}
	
	public void setRule(String[][] blocks, Object... args) {
		this.height = blocks.length;
		this.widthX = blocks[0].length;
		this.widthZ = blocks[0][0].length();
		
		Map<Character, BlockRule> target = new HashMap<Character, BlockRule>();
		Character key = null;
		for (Object arg : args) {
			if (key == null) key = (Character) arg;
			else {
				target.put(key, (BlockRule) arg);
				key = null;
			}
		}
		
		this.data = new BlockRule[this.height][this.widthX][this.widthZ];
		for (int y = 0; y < this.height; y++) {
			String[] data = blocks[y];
			for (int z = 0; z < this.widthZ; z++) {
				char[] array = data[z].toCharArray();
				for (int x = 0; x < this.widthX; x++) {
					char character = array[x];
					if (character == ' ') continue;
					
					this.data[y][z][x] = target.get(character);
				}
			}
		}
	}
	
	public BlocksChecker setOffset(int x, int y, int z) {
		this.offsetX = -x;
		this.offsetY = y;
		this.offsetZ = -z;
		
		this.centerRule = this.data[this.offsetY][this.offsetZ][this.offsetX];
		return this;
	}
	
	public BlockRule getCenterRule() {
		if (this.centerRule != null) this.centerRule = this.data[this.offsetY][this.offsetZ][this.offsetX];
		return this.centerRule;
	}
	
	public int getOffsetX() {
		return this.offsetX;
	}
	
	public int getOffsetY() {
		return this.offsetY;
	}
	
	public int getOffsetZ() {
		return this.offsetZ;
	}
	
	public boolean check(Block block) {
		return this.check(block.getLocation());
	}
	
	public boolean check(Location location) {
		location = location.clone().add(this.offsetX, this.offsetY, this.offsetZ);
		
		for (int y = 0; y < this.height; y++) for (int z = 0; z < this.widthZ; z++) for (int x = 0; x < this.widthX; x++) {
			Block block = location.clone().add(x, -y, z).getBlock();
			BlockRule rule = this.data[y][z][x];
			
			if (rule != null && !rule.check(block)) return false;
		}
		
		return true;
	}
	
}
