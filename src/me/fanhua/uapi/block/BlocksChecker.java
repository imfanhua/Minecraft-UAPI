package me.fanhua.uapi.block;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Location;
import org.bukkit.block.Block;

public class BlocksChecker implements IBlockChecker {
	
	private IBlockChecker[][][] data;
	
	private int widthX;
	private int widthZ;
	private int height;
	
	private int offsetX;
	private int offsetZ;
	private int offsetY;
	
	private IBlockChecker centerChecker;
	
	private BlocksChecker(BlocksChecker object) {
		this.widthX = object.widthX;
		this.widthZ = object.widthZ;
		this.height = object.height;
		
		if (this.widthZ == 0) {
			this.data = new IBlockChecker[0][][];
			return;
		}
		
		this.data = new IBlockChecker[this.height][this.widthX][this.widthZ];
		for (int y = 0; y < this.height; y++) for (int z = 0; z < this.widthZ; z++) for (int x = 0; x < this.widthX; x++) this.data[y][z][x] = object.data[y][z][x];
	}
	
	public BlocksChecker(String[][] data, Object... args) {
		this.setRule(data, args);
	}
	
	public void setRule(String[][] blocks, Object... args) {
		this.height = blocks.length;
		if (this.height == 0) {
			this.widthX = 0;
			this.widthZ = 0;
		} else {
			this.widthX = blocks[0].length;
			if (this.widthX == 0) this.widthZ = 0;
			else this.widthZ = blocks[0][0].length();
		}
		
		if (this.widthZ == 0) {
			this.data = new IBlockChecker[0][][];
			return;
		}
		
		Map<Character, IBlockChecker> target = new HashMap<Character, IBlockChecker>();
		Character key = null;
		for (Object arg : args) {
			if (key == null) key = (Character) arg;
			else {
				target.put(key, ((IBlockChecker) arg).copy());
				key = null;
			}
		}
		
		this.data = new IBlockChecker[this.height][this.widthX][this.widthZ];
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
		
		this.centerChecker = this.data[this.offsetY][this.offsetZ][this.offsetX];
		return this;
	}
	
	public IBlockChecker getCenterChecker() {
		if (this.centerChecker != null) this.centerChecker = this.data[this.offsetY][this.offsetZ][this.offsetX];
		return this.centerChecker;
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
	
	@Override
	public boolean check(Block block) {
		return this.check(block.getLocation());
	}
	
	@Override
	public boolean check(Location location) {
		location = location.clone().add(this.offsetX, this.offsetY, this.offsetZ);
		
		for (int y = 0; y < this.height; y++) for (int z = 0; z < this.widthZ; z++) for (int x = 0; x < this.widthX; x++) {
			Block block = location.clone().add(x, -y, z).getBlock();
			IBlockChecker rule = this.data[y][z][x];
			
			if (rule != null && !rule.check(block)) return false;
		}
		
		return true;
	}
	
	public BlocksChecker clone() {
		return new BlocksChecker(this);
	}
	
	public IBlockChecker copy() {
		return this.clone();
	}
	
}
