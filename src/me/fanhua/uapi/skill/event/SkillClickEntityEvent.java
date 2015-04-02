package me.fanhua.uapi.skill.event;

import org.bukkit.Location;
import org.bukkit.entity.Entity;

import me.fanhua.uapi.user.User;

public class SkillClickEntityEvent extends SkillClickEvent {
	
	private Entity entity;
	
	public SkillClickEntityEvent(User user, boolean leftButton, Entity entity) {
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
