package me.fanhua.uapi.user;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Player;

public class UserManager {
	
	private static final Map<String, User> map = new HashMap<String, User>();
	
	public static User getUser(Player player) {
		return UserManager.map.get(player.getUniqueId().toString());
	}
	
	public static void setUser(User user) {
		UserManager.map.put(user.getPlayer().getUniqueId().toString(), user);
	}
	
	public static void removeUser(Player player) {
		UserManager.map.remove(player.getUniqueId().toString());
	}
	
}
