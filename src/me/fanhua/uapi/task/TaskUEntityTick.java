package me.fanhua.uapi.task;

import me.fanhua.uapi.manager.TaskManager;
import me.fanhua.uapi.manager.UEntityManager;

public class TaskUEntityTick implements Runnable {
	
	public static void addTask() {
		TaskManager.add(new TaskUEntityTick(), 1);
	}
	
	private int tick;
	
	private TaskUEntityTick() {}
	
	public void run() {
		int tick = this.tick++;
		if (this.tick < 0) this.tick = 0;
		
		UEntityManager.getInstance().onTick(tick);
	}

}
