package me.fanhua.uapi.rule;

import me.fanhua.uapi.manager.RuleManager;

public interface IRule {
	
	@Deprecated
	public boolean add(RuleManager manager);
	
	@Deprecated
	public boolean remove(RuleManager manager);
	
	@Deprecated
	public void disable(RuleManager manager);
	
}
