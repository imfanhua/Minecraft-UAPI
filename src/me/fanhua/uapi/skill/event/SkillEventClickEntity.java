package me.fanhua.uapi.skill.event;

import org.bukkit.Location;
import org.bukkit.entity.Entity;

import me.fanhua.uapi.user.User;

public class SkillEventClickEntity extends SkillEventClick {
	
	private Entity entity;
	
	public SkillEventClickEntity(User user, boolean leftButton, Entity entity) {
		super(user, leftButton);
		
		this.entity = entity;
	}
	
	public Entity getEntity() {
		return this.entity;
	}
	
	public Location getLocation() {
		return this.entity.getLocation();
	}
	
}
