package me.fanhua.uapi.listener;

import me.fanhua.uapi.rule.RuleBanCommands;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class CommandsListener implements Listener {
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerCommandPreprocessEvent(PlayerCommandPreprocessEvent event) {
		if (RuleBanCommands.RULE.check(event.getMessage())) event.setCancelled(true);
	}
	
}
