package me.fanhua.uapi.utils;

import java.lang.reflect.Field;

public class EntityUtils {
	
	private static Field fieldEntityCount;
	
	static {
		try {
			EntityUtils.fieldEntityCount = ClassUtils.getField(ClassUtils.getServerClass("Entity"), "entityCount", true);
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
	
}
