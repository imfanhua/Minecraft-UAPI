package me.fanhua.uapi.listener;

import me.fanhua.uapi.event.user.UserReadyEvent;
import me.fanhua.uapi.network.Network;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

public class PacketListener implements Listener {
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onUserReadyEvent(UserReadyEvent event) {
		Network.injector(event.getPlayer());
	}
	
}
