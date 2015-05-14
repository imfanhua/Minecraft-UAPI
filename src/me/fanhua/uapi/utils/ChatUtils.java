package me.fanhua.uapi.utils;

import java.lang.reflect.Method;

import me.fanhua.uapi.network.packet.IPacket;

public class ChatUtils {
	
	private static Method methodChatSerializer;
	
	static {
		try {
			ChatUtils.methodChatSerializer = ClassUtils.getMethod(ClassUtils.getServerClass("IChatBaseComponent").getClasses()[0], "a", false, String.class);
		} catch (Throwable error) {}
	}
	
	public static Object toChatObject(String message) {
		try {
			return ChatUtils.methodChatSerializer.invoke(null, message);
		} catch (Throwable error) {
			return null;
		}
	}
	
	public static IPacket getActionPacket(Object chat) {
		return PacketUtils.getChatPacket(chat, 2);
	}
	
	public static IPacket getChatPacket(Object chat) {
		return PacketUtils.getChatPacket(chat, 1);
	}
	
}
