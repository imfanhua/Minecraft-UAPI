package me.fanhua.uapi.listener;

import me.fanhua.uapi.api.API;
import me.fanhua.uapi.event.user.UserExitEvent;
import me.fanhua.uapi.event.user.UserReadyEvent;
import me.fanhua.uapi.manager.EventManager;
import me.fanhua.uapi.manager.UserManager;
import me.fanhua.uapi.user.User;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class UserListener implements Listener {
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerJoinEvent(PlayerJoinEvent event) {
		event.setJoinMessage(null);
		
		User user = API.to(event.getPlayer());
		if (user == null) return;
		
		EventManager.call(new UserReadyEvent(user));
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerKickEvent(PlayerKickEvent event) {
		event.setLeaveMessage(null);
		this.onExit(event.getPlayer());
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerQuitEvent(PlayerQuitEvent event) {
		event.setQuitMessage(null);
		this.onExit(event.getPlayer());
	}
	
	private void onExit(Player player) {
		User user = UserManager.getUser(player);
		if (user == null) return;
		user.setOffline();
		
		EventManager.call(new UserExitEvent(user));
		UserManager.removeUser(player);
	}
	
}
