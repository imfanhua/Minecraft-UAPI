package me.fanhua.uapi.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import me.fanhua.uapi.UAPI;
import me.fanhua.uapi.rule.IRule;

public final class RuleManager {
	
	public static RuleManager getInstance() {
		return UAPI.getRuleManager();
	}
	
	private boolean disabled;
	private Map<String, IRule> rules;
	
	public RuleManager() {
		this.rules = new HashMap<String, IRule>();
	}
	
	public IRule[] getRules() {
		return new ArrayList<IRule>(this.rules.values()).toArray(new IRule[this.rules.size()]);
	}
	
	public <T extends IRule> T getRule(Class<T> type) {
		return (T) this.rules.get(type.getName());
	}
	
	public <T extends IRule> boolean hasRule(T type) {
		return this.rules.containsKey(type.getClass().getName());
	}
	
	public <T extends IRule> T addRule(T rule) {
		Class<? extends IRule> type = rule.getClass();
		T object = (T) this.getRule(type);
		if (object != null) return object;
		if (!rule.add(this)) return null;
		this.rules.put(type.getName(), rule);
		return rule;
	}
	
	public <T extends IRule> T removeRule(T rule) {
		Class<? extends IRule> type = rule.getClass();
		T object = (T) this.getRule(type);
		if (object == null) return null;
		if (!rule.remove(this)) return null;
		this.rules.remove(type.getName());
		return rule;
	}
	
	public boolean isDisabled() {
		return this.disabled;
	}
	
	public void disable() {
		if (this.disabled) return;
		this.disabled = true;
		for (IRule rule : this.rules.values()) rule.disable(this);
		this.rules = null;
	}
	
}
