package me.fanhua.uapi.task;

import me.fanhua.uapi.api.API;
import me.fanhua.uapi.manager.TaskManager;
import me.fanhua.uapi.skill.Skill;
import me.fanhua.uapi.user.User;

public class TaskSkill implements Runnable {
	
	public static void addTask() {
		TaskManager.add(new TaskSkill(), 10);
	}
	
	private TaskSkill() {}
	
	public void run() {
		for (User user : API.getUsers()) for (Skill skill : user.get(API.USER.SKILL).getSkills()) {
			skill.onTick();
			skill.getRender().draw();
		}
	}

}
