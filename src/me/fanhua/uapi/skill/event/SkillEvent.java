package me.fanhua.uapi.skill.event;

import me.fanhua.uapi.event.base.event.UEventBoth;
import me.fanhua.uapi.user.User;

import org.bukkit.entity.Player;

public abstract class SkillEvent extends UEventBoth {
	
	private User user;
	
	public SkillEvent(User user) {
		this.user = user;
	}
	
	public User getUser() {
		return this.user;
	}
	
	public Player getPlayer() {
		return this.user.getPlayer();
	}
	
}
