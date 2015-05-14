package me.fanhua.uapi.listener;

import me.fanhua.uapi.api.API;
import me.fanhua.uapi.event.user.UserReadyEvent;
import me.fanhua.uapi.manager.UEntityManager;
import me.fanhua.uapi.task.TaskUEntityPlayerMove;
import me.fanhua.uapi.user.User;

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

public class UEntityListener implements Listener {
	
	@EventHandler(priority = EventPriority.MONITOR)
	public void onUserReadyEvent(UserReadyEvent event) {
		User user = event.getUser();
		Location location = user.getLocation();
		
		UEntityManager.getInstance().onMove(user, null, location);
	}
	
	@EventHandler(priority = EventPriority.MONITOR)
	public void onPlayerMoveEvent(PlayerMoveEvent event) {
		if (event.isCancelled()) return;
		UEntityManager.getInstance().onMove(API.to(event.getPlayer()), event.getFrom(), event.getTo());
	}
	
	@EventHandler(priority = EventPriority.MONITOR)
	public void onPlayerTeleportEvent(PlayerTeleportEvent event) {
		if (event.isCancelled()) return;
		UEntityManager.getInstance().onMove(API.to(event.getPlayer()), event.getFrom(), event.getTo());
	}
	
	@EventHandler(priority = EventPriority.MONITOR)
	public void onPlayerRespawnEvent(PlayerRespawnEvent event) {
		TaskUEntityPlayerMove.addTask(event.getPlayer());
	}
	
}
