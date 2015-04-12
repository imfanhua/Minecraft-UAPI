package me.fanhua.uapi.manager;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitTask;

import me.fanhua.uapi.UAPI;

public class TaskManager {
	
	private TaskManager() {}
	
	public static void runNextTick(Runnable runnable) {
		TaskManager.runTask(runnable, 0);
	}
	
	public static void runTask(Runnable runnable, long time) {
		Bukkit.getScheduler().runTaskLater(UAPI.getInstance(), runnable, time);
	}
	
	public static BukkitTask addTask(Runnable runnable, long time) {
		return TaskManager.addTask(runnable, time, time);
	}
	
	public static BukkitTask addTask(Runnable runnable, long time, long later) {
		return Bukkit.getScheduler().runTaskTimer(UAPI.getInstance(), runnable, time, later);
	}
	
}
