package me.fanhua.uapi.event;

import me.fanhua.uapi.user.User;

import org.bukkit.event.HandlerList;

public class UserReadyEvent extends UserEvent {
	
	private static final HandlerList handlers = new HandlerList();
	
	public UserReadyEvent(User who) {
		super(who);
	}
	
	@Override
	public HandlerList getHandlers() {
		return UserReadyEvent.handlers;
	}
	
	public static HandlerList getHandlerList() {
		return UserReadyEvent.handlers;
	}
	
}
