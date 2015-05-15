package me.fanhua.uapi.rule;

import me.fanhua.uapi.UAPI;
import me.fanhua.uapi.listener.UItemListener;
import me.fanhua.uapi.manager.EventManager;
import me.fanhua.uapi.manager.RuleManager;
import me.fanhua.uapi.manager.UItemManager;

public final class RuleUItem implements IRule {
	
	public static final RuleUItem RULE = new RuleUItem();
	
	private UItemManager manager;
	
	private RuleUItem() {}
	
	public UItemManager getManager() {
		return this.manager;
	}
	
	public void setManager(UItemManager manager) {
		if (manager == null) return;
		this.manager = manager;
	}
	
	@Override
	@Deprecated
	public boolean add(RuleManager manager) {
		manager.addRule(RuleUser.RULE);
		
		UItemManager.newInstance();
		
		EventManager.addListener(UAPI.getInstance(), new UItemListener());
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
		UItemManager.getInstance().clear();
	}

}
