package me.fanhua.uapi.skill.event;

import me.fanhua.uapi.user.User;

public class SkillClickEvent extends SkillEvent {
	
	public boolean leftButton;
	
	public SkillClickEvent(User user, boolean leftButton) {
		super(user);
		
		this.leftButton = leftButton;
	}
	
	public boolean isLeftButton() {
		return this.leftButton;
	}
	
	public boolean isRightButton() {
		return !this.leftButton;
	}
	
	public boolean isNotLeftButton() {
		return !this.leftButton;
	}
	
	public boolean isNotRightButton() {
		return this.leftButton;
	}
	
}
