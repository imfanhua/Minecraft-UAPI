package me.fanhua.uapi.network;

import org.bukkit.entity.Player;

import me.fanhua.uapi.utils.ClassUtils;

public class Network {
	
	public static boolean send(Player player, Object packet) throws Throwable {
		Object object = ClassUtils.getField(ClassUtils.getCraftClass("entity.CraftEntity"), "entity", true).get(player);
		object = ClassUtils.getField(object.getClass(), "playerConnection", true).get(object);
		
		if (object == null) return false;
		
		/*
			Object packet = ClassUtils.getServerClass("PacketPlayOutCustomPayload").newInstance();
			ClassUtils.getField(packet.getClass(), "tag", true).set(packet, channel);
			ClassUtils.getField(packet.getClass(), "data", true).set(packet, bytes);
		*/
		
		ClassUtils.getMethod(object.getClass(), "sendPacket", true, ClassUtils.getServerClass("Packet")).invoke(object, packet);
		return true;
	}
	
}