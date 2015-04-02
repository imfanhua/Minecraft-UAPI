package me.fanhua.uapi.task;

import me.fanhua.uapi.UAPI;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class TaskReloadJoin implements Runnable {
	
	public static void addTask() {
		UAPI.runNextTick(new TaskReloadJoin());
	}
	
	private TaskReloadJoin() {}
	
	public void run() {
		for (Player player : Bukkit.getOnlinePlayers()) Bukkit.getPluginManager().callEvent(new PlayerQuitEvent(player, null));
		for (Player player : Bukkit.getOnlinePlayers()) Bukkit.getPluginManager().callEvent(new PlayerJoinEvent(player, null));
	}

}
