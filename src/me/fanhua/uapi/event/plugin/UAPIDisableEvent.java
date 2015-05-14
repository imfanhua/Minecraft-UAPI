package me.fanhua.uapi.event.plugin;

import org.bukkit.event.HandlerList;

public class UAPIDisableEvent extends UAPIEvent {
	
	private static final HandlerList handlers = new HandlerList();
	
	@Override
	public HandlerList getHandlers() {
		return UAPIDisableEvent.handlers;
	}
	
	public static HandlerList getHandlerList() {
		return UAPIDisableEvent.handlers;
	}
	
}
