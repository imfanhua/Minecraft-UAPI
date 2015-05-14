package me.fanhua.uapi.rule;

import me.fanhua.uapi.UAPI;
import me.fanhua.uapi.listener.UEntityListener;
import me.fanhua.uapi.manager.EventManager;
import me.fanhua.uapi.manager.RuleManager;
import me.fanhua.uapi.manager.UEntityManager;
import me.fanhua.uapi.task.TaskUEntityTick;

public final class RuleUEntity implements IRule {
	
	public static final RuleUEntity RULE = new RuleUEntity();
	
	private UEntityManager manager;
	
	private RuleUEntity() {}
	
	public UEntityManager getManager() {
		return this.manager;
	}
	
	public void setManager(UEntityManager manager) {
		if (manager == null) return;
		this.manager = manager;
	}
	
	@Override
	@Deprecated
	public boolean add(RuleManager manager) {
		manager.addRule(RuleUser.RULE);
		
		UEntityManager.newInstance();
		
		TaskUEntityTick.addTask();
		EventManager.addListener(UAPI.getInstance(), new UEntityListener());
		return true;
	}

	@Override
	@Deprecated
	public boolean remove(RuleManager manager) {
		return false;
	}

	@Override
	@Deprecated
	public void disable(RuleManager manager) {
		UEntityManager.getInstance().clear();
	}

}
