package me.fanhua.uapi.manager;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitTask;

import me.fanhua.uapi.UAPI;

public final class TaskManager {
	
	private TaskManager() {}
	
	public static void tick(Runnable runnable) {
		TaskManager.run(runnable, 0);
	}
	
	public static void run(Runnable runnable, long time) {
		Bukkit.getScheduler().runTaskLater(UAPI.getInstance(), runnable, time);
	}
	
	public static BukkitTask add(Runnable runnable, long time) {
		return TaskManager.add(runnable, time, time);
	}
	
	public static BukkitTask add(Runnable runnable, long time, long later) {
		return Bukkit.getScheduler().runTaskTimer(UAPI.getInstance(), runnable, time, later);
	}
	
}
