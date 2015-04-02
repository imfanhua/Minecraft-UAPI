package me.fanhua.uapi.event;

import me.fanhua.uapi.user.User;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;

public abstract class UserEvent extends Event {
	
	private User user;
	
	public UserEvent(User user) {
		this.user = user;
	}
	
	public User getUser() {
		return this.user;
	}
	
	public Player getPlayer() {
		return this.user.getPlayer();
	}
	
}
