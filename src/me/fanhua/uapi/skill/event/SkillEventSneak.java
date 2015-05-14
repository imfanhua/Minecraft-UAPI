package me.fanhua.uapi.skill.event;

import me.fanhua.uapi.user.User;

public class SkillEventSneak extends SkillEvent {
	
	private boolean sneak;
	
	public SkillEventSneak(User user, boolean sneak) {
		super(user);
		
		this.sneak = sneak;
	}
	
	public boolean isGoSneak() {
		return this.sneak;
	}
	
	public boolean isNowSneak() {
		return !this.sneak;
	}
	
}
