package me.fanhua.uapi.user.api;

import me.fanhua.uapi.skill.Skill;
import me.fanhua.uapi.skill.SkillManager;
import me.fanhua.uapi.user.User;

public class UserSkillManager {
	
	public static UserSkillManager create(User user) {
		return new UserSkillManager(user);
	}
	
	private User user;
	private SkillManager skill;
	
	private UserSkillManager(User user) {
		this.user = user;
		this.skill = new SkillManager();
	}
	
	public User getUser() {
		return this.user;
	}
	
	public <T extends Skill> T getSkill(Class<? extends T> type) {
		for (Skill skill : this.skill.getSkills()) if (skill.getClass().getName().equals(type.getName())) {
			try {
				return (T) skill;
			} catch (Throwable error) {}
		}
		
		return null;
	}
	
	public boolean hasSkill(Class<? extends Skill> type) {
		return this.getSkill(type) != null;
	}
	
	public void addSkill(Skill skill) {
		if (this.skill.hasSkill(skill)) return;
		this.skill.addSkill(skill);
		skill.getRender().render();
	}
	
	public Skill[] getSkills() {
		return this.skill.getSkills().toArray(new Skill[0]);
	}
	
	public void removeSkill(Skill skill) {
		this.skill.removeSkill(skill);
		skill.remove();
	}
	
	public void removeAll() {
		for (Skill skill : this.skill.getSkills()) skill.remove();
		this.skill.clear();
	}
	
}
