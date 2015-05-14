package me.fanhua.uapi.manager;

import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

public final class EventManager {
	
	public static void call(Event event) {
		Bukkit.getPluginManager().callEvent(event);
	}
	
	public static void addListener(Plugin plugin, Listener listener) {
		Bukkit.getPluginManager().registerEvents(listener, plugin);
	}
	
}
