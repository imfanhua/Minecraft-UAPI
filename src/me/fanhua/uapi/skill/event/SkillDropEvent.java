package me.fanhua.uapi.skill.event;

import org.bukkit.entity.Item;

import me.fanhua.uapi.user.User;

public class SkillDropEvent extends SkillEvent {
	
	private Item item;
	
	public SkillDropEvent(User user, Item item) {
		super(user);
		
		this.item = item;
	}
	
	public void remove() {
		this.item.remove();
	}
	
}
