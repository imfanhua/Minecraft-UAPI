package me.fanhua.uapi.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.fanhua.uapi.user.User;
import me.fanhua.uapi.user.manager.base.IManagerRegister;

import org.bukkit.entity.Player;

public final class UserManager {
	
	private static final Map<String, User> users = new HashMap<String, User>();
	private static final Map<String, IManagerRegister> managers = new HashMap<String, IManagerRegister>();
	
	@Deprecated
	public static List<User> getUsers() {
		return new ArrayList<User>(UserManager.users.values());
	}
	
	@Deprecated
	public static User getUser(Player player) {
		return UserManager.users.get(player.getUniqueId().toString());
	}
	
	@Deprecated
	public static void setUser(User user) {
		UserManager.users.put(user.getPlayer().getUniqueId().toString(), user);
	}
	
	@Deprecated
	public static void removeUser(Player player) {
		UserManager.users.remove(player.getUniqueId().toString());
	}
	
	@Deprecated
	public static void clear() {
		for (User user : UserManager.users.values()) user.setOffline();
		UserManager.users.clear();
	}
	
	@Deprecated
	public static void callRegisters(User user) {
		for (IManagerRegister register : UserManager.managers.values()) register.register(user);
	}
	
	public static <T extends IManagerRegister> T getRegister(Class<T> type) {
		return (T) UserManager.managers.get(type.getName());
	}
	
	public static <T extends IManagerRegister> T addRegister(T manager) {
		Class<? extends IManagerRegister> type = manager.getClass();
		T object = (T) UserManager.getRegister(type);
		if (object != null) return object;
		UserManager.managers.put(type.getName(), manager);
		return manager;
	}
	
	public static IManagerRegister[] getRegisters() {
		return new ArrayList<IManagerRegister>(UserManager.managers.values()).toArray(new IManagerRegister[UserManager.managers.size()]);
	}
	
	public static IManagerRegister[] clearRegister() {
		IManagerRegister[] managers = UserManager.getRegisters();
		UserManager.managers.clear();
		return managers;
	}
	
	public static int getRegisterSize() {
		return UserManager.managers.size();
	}
	
}
