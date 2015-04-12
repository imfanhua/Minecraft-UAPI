package me.fanhua.uapi.manager;

import me.fanhua.uapi.UAPI;
import me.fanhua.uapi.listener.CommandListener;
import me.fanhua.uapi.listener.GuiListener;
import me.fanhua.uapi.listener.NoBreakListener;
import me.fanhua.uapi.listener.SkillListener;
import me.fanhua.uapi.task.TaskGuiTick;
import me.fanhua.uapi.task.TaskSkill;

import org.bukkit.World;

public class RuleManager {
	
	public static RuleManager getInstance() {
		return UAPI.getRuleManager();
	}
	
	private String reloadKick;
	private boolean ruleGui;
	private boolean ruleBanCommands;
	private boolean ruleNoBreak;
	private boolean ruleSkill;
	
	public RuleManager() {
		this.reloadKick = null;
		
		this.ruleGui = false;
		this.ruleBanCommands = false;
		this.ruleNoBreak = false;
		this.ruleSkill = false;
	}
	
	public void addReloadKick(String message) {
		this.reloadKick = message;
	}
	
	public String getReloadKick() {
		return this.reloadKick;
	}
	
	public void removeReloadKick() {
		this.reloadKick = null;
	}
	
	public void addGuiRule() {
		if (this.ruleGui) return;
		this.ruleGui = true;
		
		UAPI.addListener(UAPI.getInstance(), new GuiListener());
		TaskGuiTick.addTask();
	}
	
	public void addBanCommandsRule() {
		if (this.ruleBanCommands) return;
		this.ruleBanCommands = true;
		
		UAPI.addListener(UAPI.getInstance(), new CommandListener());
	}
	
	public void addNoBreakRule() {
		if (this.ruleNoBreak) return;
		this.ruleNoBreak = true;
		
		UAPI.addListener(UAPI.getInstance(), new NoBreakListener());
	}
	
	public void addNoBreakRule(World world) {
		if (this.ruleNoBreak) return;
		
		UAPI.addListener(UAPI.getInstance(), new NoBreakListener(world.getName()));
	}
	
	public void addNoBreakRule(String world) {
		if (this.ruleNoBreak) return;
		
		UAPI.addListener(UAPI.getInstance(), new NoBreakListener(world));
	}
	
	public void addSkillRule() {
		if (this.ruleSkill) return;
		this.ruleSkill = true;
		
		TaskSkill.addTask();
		UAPI.addListener(UAPI.getInstance(), new SkillListener());
	}
	
}
