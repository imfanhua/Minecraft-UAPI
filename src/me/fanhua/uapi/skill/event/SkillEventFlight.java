package me.fanhua.uapi.skill.event;

import me.fanhua.uapi.user.User;

public class SkillEventFlight extends SkillEvent {
	
	private boolean flying;
	
	public SkillEventFlight(User user, boolean flying) {
		super(user);
		
		this.flying = flying;
	}
	
	public boolean isGoFlying() {
		return this.flying;
	}
	
	public boolean isNowFlying() {
		return !this.flying;
	}
	
}
