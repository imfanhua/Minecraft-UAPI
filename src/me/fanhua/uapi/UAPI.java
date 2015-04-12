package me.fanhua.uapi;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import me.fanhua.uapi.listener.PlayerListener;
import me.fanhua.uapi.manager.MapManager;
import me.fanhua.uapi.manager.RuleManager;

public class UAPI extends JavaPlugin {
	
	private static UAPI instance;
	
	public static UAPI getInstance() {
		return UAPI.instance;
	}
	
	private static RuleManager rules;
	
	@Override
	public void onEnable() {
		UAPI.instance = this;
		
		UAPI.rules = new RuleManager();
		
		UAPI.addListener(this, new PlayerListener());
	}
	
	@Override
	public void onDisable() {
		String reloadKick = UAPI.rules.getReloadKick();
		if (reloadKick != null) for (Player player : Bukkit.getOnlinePlayers()) player.kickPlayer(reloadKick);
	}
	
	public static RuleManager getRuleManager() {
		return UAPI.rules;
	}
	
	public static MapManager getMapManager() {
		return MapManager.getInstance();
	}
	
	public static void addListener(Plugin plugin, Listener listener) {
		Bukkit.getPluginManager().registerEvents(listener, plugin);
	}
	
}
