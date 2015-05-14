package me.fanhua.uapi.api;

import java.util.List;

import me.fanhua.uapi.UAPI;
import me.fanhua.uapi.entity.UEntity;
import me.fanhua.uapi.manager.MapManager;
import me.fanhua.uapi.manager.RuleManager;
import me.fanhua.uapi.manager.UEntityManager;
import me.fanhua.uapi.manager.UItemManager;
import me.fanhua.uapi.manager.UserManager;
import me.fanhua.uapi.rule.IRule;
import me.fanhua.uapi.rule.RuleBanCommands;
import me.fanhua.uapi.rule.RuleNoBreak;
import me.fanhua.uapi.rule.RuleReloadKick;
import me.fanhua.uapi.rule.RuleUEntity;
import me.fanhua.uapi.rule.RuleUItem;
import me.fanhua.uapi.rule.RuleUser;
import me.fanhua.uapi.rule.RuleUserGui;
import me.fanhua.uapi.rule.RuleUserHookPacket;
import me.fanhua.uapi.rule.RuleUserSkill;
import me.fanhua.uapi.user.User;
import me.fanhua.uapi.user.manager.UserChatManager;
import me.fanhua.uapi.user.manager.UserGuiManager;
import me.fanhua.uapi.user.manager.UserSkillManager;
import me.fanhua.uapi.user.manager.UserUtilsManager;
import me.fanhua.uapi.user.manager.base.IUserManager;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class API {
	
	public static class UTILS {
		
		public static final Class<UAPI> UAPI = UAPI.class;
		public static final Class<RuleManager> RULE = RuleManager.class;
		public static final Class<MapManager> MAP = MapManager.class;
		public static final Class<UEntityManager> ENTITY = UEntityManager.class;
		public static final Class<UItemManager> ITEM = UItemManager.class;
		
		private static <T> T get(Class<T> type) {
			if (type == UTILS.UAPI) return (T) me.fanhua.uapi.UAPI.getInstance();
			else if (type == UTILS.RULE) return (T) RuleManager.getInstance();
			else if (type == UTILS.MAP) return (T) MapManager.getInstance();
			else if (type == UTILS.ENTITY) return (T) UEntityManager.getInstance();
			else if (type == UTILS.ITEM) return (T) UItemManager.getInstance();
			return null;
		}
		
	}
	
	public static class RULE {
		
		public static final RuleBanCommands BAN_COMMANDS = RuleBanCommands.RULE;
		public static final RuleNoBreak NO_BREAK = RuleNoBreak.RULE;
		public static final RuleReloadKick RELOAD_KICK = RuleReloadKick.RULE;
		
		public static final RuleUser USER = RuleUser.RULE;
		public static final RuleUserGui USER_GUI = RuleUserGui.RULE;
		public static final RuleUserSkill USER_SKILL = RuleUserSkill.RULE;
		public static final RuleUserHookPacket USER_HOOK_PACKET = RuleUserHookPacket.RULE;
		
		public static final RuleUEntity UENTITY = RuleUEntity.RULE;
		public static final RuleUItem UITEM = RuleUItem.RULE;
		
		private RULE() {}
		
	}
	
	public static class USER {
		
		public static final Class<UserChatManager> CHAT = UserChatManager.class;
		public static final Class<UserSkillManager> SKILL = UserSkillManager.class;
		public static final Class<UserGuiManager> GUI = UserGuiManager.class;
		public static final Class<UserUtilsManager> UTILS = UserUtilsManager.class;
		
		private USER() {}
		
	}
	
	// Utils
	
	public static <T> T get(Class<T> type) {
		return UTILS.get(type);
	}
	
	// Rule
	
	public static <T extends IRule> T add(T rule) {
		return UAPI.getRuleManager().addRule(rule);
	}
	
	public static <T extends IRule> T getRule(Class<T> type) {
		return UAPI.getRuleManager().getRule(type);
	}
	
	public static <T extends IRule> T removeRule(T rule) {
		return UAPI.getRuleManager().removeRule(rule);
	}
	
	// UserManager
	
	public static <T extends IUserManager> T get(Player player, Class<T> type) {
		return API.get(User.toUser(player), type);
	}
	
	public static <T extends IUserManager> T get(User user, Class<T> type) {
		return user.getManager(type);
	}
	
	// User
	
	public static User to(Entity entity) {
		if (entity.getType() != EntityType.PLAYER) return null;
		return User.toUser((Player) entity);
	}
	
	public static User to(Player player) {
		return User.toUser(player);
	}
	
	public static List<User> getUsers() {
		return UserManager.getUsers();
	}
	
	// UEntity
	
	public static void spawn(UEntity entity) {
		UEntityManager.getInstance().spawn(entity);
	}
	
	public static void remove(UEntity entity) {
		UEntityManager.getInstance().remove(entity);
	}
	
	public static UEntity getEntity(int id) {
		return UEntityManager.getInstance().getEntity(id);
	}
	
	public List<UEntity> getEntities() {
		return UEntityManager.getInstance().getEntities();
	}
	
	public <T extends UEntity> List<T> getEntities(Class<T> type) {
		return UEntityManager.getInstance().getEntities(type);
	}
	
	// Utils
	
	public static MapManager getMapManager() {
		return MapManager.getInstance();
	}
	
}
