package me.fanhua.uapi.skill.event;

import me.fanhua.uapi.user.User;

public class SkillSprintEvent extends SkillEvent {
	
	private boolean sprint;
	
	public SkillSprintEvent(User user, boolean sprint) {
		super(user);
		
		this.sprint = sprint;
	}
	
	public boolean isGoSprint() {
		return this.sprint;
	}
	
	public boolean isNowSprint() {
		return !this.sprint;
	}
	
}
