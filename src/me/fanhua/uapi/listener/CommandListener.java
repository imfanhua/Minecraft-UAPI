package me.fanhua.uapi.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class CommandListener implements Listener {
	
	private static final String[] commands = new String[] {
		"kill",
		"me",
		"help",
		"?",
		"plugins",
		"pl",
		"tell",
		"msg",
		"m",
		"w",
		"version",
		"about",
		"ver",
		"list"
	};
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerCommandPreprocessEvent(PlayerCommandPreprocessEvent event) {
		String message = event.getMessage().toLowerCase();
		
		for (String command : CommandListener.commands) if (message.equals("/" + command) || message.startsWith("/" + command + " ")) {
			event.setCancelled(true);
			return;
		}
	}
	
}
