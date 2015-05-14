package me.fanhua.uapi.task;

import org.bukkit.entity.Player;

import me.fanhua.uapi.api.API;
import me.fanhua.uapi.manager.TaskManager;
import me.fanhua.uapi.manager.UEntityManager;
import me.fanhua.uapi.user.User;

public class TaskUEntityPlayerMove implements Runnable {
	
	public static void addTask(Player player) {
		TaskManager.tick(new TaskUEntityPlayerMove(player));
	}
	
	private Player player;
	
	private TaskUEntityPlayerMove(Player player) {
		this.player = player;
	}
	
	@Override
	public void run() {
		User user = API.to(this.player);
		if (user == null) return;
		UEntityManager.getInstance().onMove(user, null, user.getLocation());
	}
}
