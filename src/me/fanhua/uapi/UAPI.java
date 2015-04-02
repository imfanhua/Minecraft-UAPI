package me.fanhua.uapi;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import me.fanhua.uapi.listener.CommandListener;
import me.fanhua.uapi.listener.NoBreakListener;
import me.fanhua.uapi.listener.PlayerListener;
import me.fanhua.uapi.listener.SkillListener;
import me.fanhua.uapi.task.TaskReloadJoin;
import me.fanhua.uapi.task.TaskSkill;

public class UAPI extends JavaPlugin {
	
	private static UAPI instance;
	
	public static UAPI getInstance() {
		return UAPI.instance;
	}
	
	public void onEnable() {
		UAPI.instance = this;
		
		Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);
		
		UAPI.ruleBanCommands = false;
		UAPI.ruleNoBreak = false;
		UAPI.ruleSkill = false;
		
		TaskReloadJoin.addTask();
	}
	
	private static boolean ruleBanCommands;
	
	public static void addBanCommandsRule() {
		if (UAPI.ruleBanCommands) return;
		UAPI.ruleBanCommands = true;
		
		UAPI.addListener(new CommandListener());
	}
	
	private static boolean ruleNoBreak;
	
	public static void addNoBreakRule() {
		if (UAPI.ruleNoBreak) return;
		UAPI.ruleNoBreak = true;
		
		UAPI.addListener(new NoBreakListener());
	}
	
	public static void addNoBreakRule(World world) {
		if (UAPI.ruleNoBreak) return;
		
		UAPI.addListener(new NoBreakListener(world.getName()));
	}
	
	public static void addNoBreakRule(String world) {
		if (UAPI.ruleNoBreak) return;
		
		UAPI.addListener(new NoBreakListener(world));
	}
	
	private static boolean ruleSkill;
	
	public static void addSkillRule() {
		if (UAPI.ruleSkill) return;
		UAPI.ruleSkill = true;
		
		TaskSkill.addTask();
		UAPI.addListener(new SkillListener());
	}
	
	public static void runNextTick(Runnable runnable) {
		UAPI.runTask(runnable, 0);
	}
	
	public static void runTask(Runnable runnable, long time) {
		Bukkit.getScheduler().runTaskLater(UAPI.instance, runnable, time);
	}
	
	public static BukkitTask addTask(Runnable runnable, long time) {
		return UAPI.addTask(runnable, time, time);
	}
	
	public static BukkitTask addTask(Runnable runnable, long time, long later) {
		return Bukkit.getScheduler().runTaskTimer(UAPI.instance, runnable, time, later);
	}
	
	public static void addListener(Listener listener) {
		Bukkit.getPluginManager().registerEvents(listener, UAPI.instance);
	}
	
}
