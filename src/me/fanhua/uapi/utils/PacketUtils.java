package me.fanhua.uapi.utils;

import java.lang.reflect.Constructor;

public class PacketUtils {
	
	private static Constructor<?> constructorBlockPosition;
	
	static {
		try {
			PacketUtils.constructorBlockPosition = ClassUtils.getConstructor(ClassUtils.getServerClass("BlockPosition"), false, int.class, int.class, int.class);
		} catch (Throwable error) {}
	}
	
	public static Object newBlockPosition(int x, int y, int z) {
		try {
			return PacketUtils.constructorBlockPosition.newInstance(x, y, z);
		} catch (Throwable error) {
			return null;
		}
	}
	
}
