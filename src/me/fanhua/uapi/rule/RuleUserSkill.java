package me.fanhua.uapi.rule;

import me.fanhua.uapi.UAPI;
import me.fanhua.uapi.listener.SkillListener;
import me.fanhua.uapi.manager.EventManager;
import me.fanhua.uapi.manager.RuleManager;
import me.fanhua.uapi.manager.UserManager;
import me.fanhua.uapi.task.TaskSkill;
import me.fanhua.uapi.user.manager.UserSkillManager;

public class RuleUserSkill implements IRule {
	
	public static final RuleUserSkill RULE = new RuleUserSkill();
	
	private RuleUserSkill() {}
	
	@Override
	@Deprecated
	public boolean add(RuleManager manager) {
		manager.addRule(RuleUser.RULE);
		
		UserManager.addRegister(UserSkillManager.REGISTER);
		
		TaskSkill.addTask();
		EventManager.addListener(UAPI.getInstance(), new SkillListener());
		return true;
	}
	
	@Override
	@Deprecated
	public boolean remove(RuleManager manager) {
		return false;
	}
	
	@Override
	@Deprecated
	public void disable(RuleManager manager) {}

}
