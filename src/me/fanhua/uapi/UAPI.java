 package me.fanhua.uapi;

import org.bukkit.plugin.java.JavaPlugin;

import me.fanhua.uapi.event.plugin.UAPIDisableEvent;
import me.fanhua.uapi.manager.EventManager;
import me.fanhua.uapi.manager.RuleManager;

public final class UAPI extends JavaPlugin {
	
	private static UAPI instance;
	
	public static UAPI getInstance() {
		return UAPI.instance;
	}
	
	private static RuleManager rules;
	
	@Override
	public void onEnable() {
		UAPI.instance = this;
		
		UAPI.rules = new RuleManager();
	}
	
	@Override
	public void onDisable() {
		EventManager.call(new UAPIDisableEvent());
		UAPI.rules.disable();
	}
	
	public static RuleManager getRuleManager() {
		return UAPI.rules;
	}
	
}
