package me.fanhua.uapi.skill.event;

import org.bukkit.Location;
import org.bukkit.block.Block;

import me.fanhua.uapi.user.User;

public class SkillEventClickBlock extends SkillEventClick {
	
	private Block block;
	
	public SkillEventClickBlock(User user, boolean leftButton, Block block) {
		super(user, leftButton);
		
		this.block = block;
	}
	
	public boolean isAir() {
		return this.block == null;
	}
	
	public boolean isNotAir() {
		return this.block != null;
	}
	
	public Block getBlock() {
		return this.block;
	}
	
	public Location getLocation() {
		return this.block.getLocation();
	}
	
}
