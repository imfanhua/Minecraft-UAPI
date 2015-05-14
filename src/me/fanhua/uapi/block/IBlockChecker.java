package me.fanhua.uapi.block;

import org.bukkit.Location;
import org.bukkit.block.Block;

public interface IBlockChecker {
	
	public boolean check(Location location);
	public boolean check(Block block);
	
	public IBlockChecker copy();
	
}
