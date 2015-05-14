package me.fanhua.uapi.user.manager;

import me.fanhua.uapi.skill.Skill;
import me.fanhua.uapi.skill.SkillManager;
import me.fanhua.uapi.user.User;
import me.fanhua.uapi.user.manager.base.IManagerRegister;
import me.fanhua.uapi.user.manager.base.IUserManager;

public final class UserSkillManager implements IUserManager {
	
	public static final IManagerRegister REGISTER = new Register();
	
	private static class Register implements IManagerRegister {

		@Override
		public void register(User user) {
			user.addManager(new UserSkillManager(user));
		}
		
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
		skill.getRender().draw();
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

	@Override
	public void onOffline() {
		this.removeAll();
	}
	
}
