package me.fanhua.uapi.task;

import me.fanhua.uapi.UAPI;
import me.fanhua.uapi.gui.Gui;
import me.fanhua.uapi.user.User;

public class TaskOpenGui implements Runnable {
	
	public static void addTask(User user, Gui gui) {
		UAPI.runNextTick(new TaskOpenGui(user, gui));
	}
	
	private User user;
	private Gui gui;
	
	private TaskOpenGui(User user, Gui gui) {
		this.user = user;
		this.gui = gui;
	}
	
	public void run() {
		if (this.user.isOffline() || this.user.hasGui()) return;
		this.user.openGui(this.gui);
	}

}
