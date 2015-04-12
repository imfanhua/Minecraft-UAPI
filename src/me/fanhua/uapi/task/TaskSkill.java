package me.fanhua.uapi.task;

import me.fanhua.uapi.manager.TaskManager;
import me.fanhua.uapi.skill.Skill;
import me.fanhua.uapi.user.User;
import me.fanhua.uapi.user.UserManager;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class TaskSkill implements Runnable {
	
	public static void addTask() {
		TaskManager.addTask(new TaskSkill(), 10);
	}
	
	private TaskSkill() {}
	
	public void run() {
		for (Player player : Bukkit.getOnlinePlayers()) {
			User user = UserManager.getUser(player);
			if (user == null) continue;
			
			for (Skill skill : user.getSkillManager().getSkills()) {
				skill.onTick();
				skill.getRender().render();
			}
		}
	}

}
