package me.fanhua.uapi.utils;

import java.lang.reflect.Field;

import org.bukkit.entity.Entity;

public class EntityUtils {
	
	private static Field fieldEntityCount;
	private static Field fieldEntity;
	
	static {
		try {
			EntityUtils.fieldEntityCount = ClassUtils.getField(ClassUtils.getServerClass("Entity"), "entityCount", true);
			EntityUtils.fieldEntity = ClassUtils.getField(ClassUtils.getCraftClass("entity.CraftEntity"), "entity", true);
		} catch (Throwable error) {}
	}
	
	public static int newEntityId() {
		try {
			int id = (int) EntityUtils.fieldEntityCount.get(null);
			EntityUtils.fieldEntityCount.set(null, id + 1);
			return id;
		} catch (Throwable error) {
			return -1;
		}
	}
	
	public static Object getOriginalEntity(Entity entity) {
		try {
			return EntityUtils.fieldEntity.get(entity);
		} catch (Throwable error) {
			return null;
		}
	}
	
}
