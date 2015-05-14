package me.fanhua.uapi.utils;

import java.lang.reflect.Constructor;

import me.fanhua.uapi.network.packet.IPacket;
import me.fanhua.uapi.network.packet.Packet;

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
	
	public static IPacket getChatPacket(Object chat, int type) {
		return Packet.wrapper(Packet.OUT, "Chat").write(chat, (byte) type);
	}
	
	public static IPacket newParticlesPacket(Object effect, boolean force, int[] args, float x, float y, float z, float offsetX, float offsetY, float offsetZ, float speed, int number) {
		return Packet.wrapper(Packet.OUT, "WorldParticles").write(effect).write(x, y, z).write(offsetX, offsetY, offsetZ).write(speed, number).write(force, args);
	}
	
	public static IPacket newBreakAnimationPacket(int id, int x, int y, int z, int progress) {
		return Packet.wrapper(Packet.OUT, "BlockBreakAnimation").write(id).write(PacketUtils.newBlockPosition(x, y, z)).write(progress);
	}
	
}
