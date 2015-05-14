package me.fanhua.uapi.event.user;

import me.fanhua.uapi.user.User;

import org.bukkit.event.HandlerList;

public class UserExitEvent extends UserEvent {
	
	private static final HandlerList handlers = new HandlerList();
	
	@Override
	public HandlerList getHandlers() {
		return UserExitEvent.handlers;
	}
	
	public static HandlerList getHandlerList() {
		return UserExitEvent.handlers;
	}
	
	public UserExitEvent(User who) {
		super(who);
	}

}
