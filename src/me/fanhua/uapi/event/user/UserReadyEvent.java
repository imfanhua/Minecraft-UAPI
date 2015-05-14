package me.fanhua.uapi.event.user;

import me.fanhua.uapi.user.User;

import org.bukkit.event.HandlerList;

public class UserReadyEvent extends UserEvent {
	
	private static final HandlerList handlers = new HandlerList();
	
	@Override
	public HandlerList getHandlers() {
		return UserReadyEvent.handlers;
	}
	
	public static HandlerList getHandlerList() {
		return UserReadyEvent.handlers;
	}
	
	public UserReadyEvent(User who) {
		super(who);
	}
	
}
