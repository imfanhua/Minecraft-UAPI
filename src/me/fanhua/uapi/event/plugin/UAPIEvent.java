package me.fanhua.uapi.event.plugin;

import me.fanhua.uapi.UAPI;
import org.bukkit.event.Event;

public abstract class UAPIEvent extends Event {
	
	public UAPIEvent() {}
	
	public UAPI getUAPI() {
		return UAPI.getInstance();
	}
	
}
