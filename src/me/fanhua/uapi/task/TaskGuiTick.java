package me.fanhua.uapi.task;

import me.fanhua.uapi.api.API;
import me.fanhua.uapi.gui.Gui;
import me.fanhua.uapi.manager.TaskManager;
import me.fanhua.uapi.user.User;

public class TaskGuiTick implements Runnable {
	
	public static void addTask() {
		TaskManager.add(new TaskGuiTick(), 1);
	}
	
	private TaskGuiTick() {}
	
	public void run() {
		for (User user : API.getUsers()) {
			Gui gui = user.get(API.USER.GUI).getGui();
			if (gui == null) continue;
			gui.onTickTime();
		}
	}

}
