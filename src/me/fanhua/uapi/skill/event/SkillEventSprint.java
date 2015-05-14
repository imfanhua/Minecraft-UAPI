package me.fanhua.uapi.skill.event;

import me.fanhua.uapi.user.User;

public class SkillEventSprint extends SkillEvent {
	
	private boolean sprint;
	
	public SkillEventSprint(User user, boolean sprint) {
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
