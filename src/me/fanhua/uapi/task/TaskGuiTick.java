package me.fanhua.uapi.task;

import me.fanhua.uapi.UAPI;
import me.fanhua.uapi.gui.Gui;
import me.fanhua.uapi.user.User;
import me.fanhua.uapi.user.UserManager;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class TaskGuiTick implements Runnable {
	
	public static void addTask() {
		UAPI.addTask(new TaskGuiTick(), 1);
	}
	
	private TaskGuiTick() {}
	
	public void run() {
		for (Player player : Bukkit.getOnlinePlayers()) {
			User user = UserManager.getUser(player);
			if (user == null) continue;
			Gui gui = user.getGui();
			if (gui == null) continue;
			gui.onTickTime();
		}
	}

}
