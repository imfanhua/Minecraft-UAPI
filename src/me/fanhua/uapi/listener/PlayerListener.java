package me.fanhua.uapi.listener;

import me.fanhua.uapi.event.UserExitEvent;
import me.fanhua.uapi.event.UserReadyEvent;
import me.fanhua.uapi.user.User;
import me.fanhua.uapi.user.UserManager;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerListener implements Listener {
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerJoinEvent(PlayerJoinEvent event) {
		event.setJoinMessage(null);
		
		User user = User.toUser(event.getPlayer());
		if (user == null) return;
		user.resetAll();
		
		Bukkit.getPluginManager().callEvent(new UserReadyEvent(user));
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
		
		user.getGuiManager().close(false);
		user.getSkillManager().removeAll();
		
		Bukkit.getPluginManager().callEvent(new UserExitEvent(user));
		UserManager.removeUser(player);
	}
	
}
