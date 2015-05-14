package me.fanhua.uapi.skill.event;

import me.fanhua.uapi.user.User;

public class SkillEventClick extends SkillEvent {
	
	public boolean leftButton;
	
	public SkillEventClick(User user, boolean leftButton) {
		super(user);
		
		this.leftButton = leftButton;
	}
	
	public boolean isLeftButton() {
		return this.leftButton;
	}
	
	public boolean isRightButton() {
		return !this.leftButton;
	}
	
}
