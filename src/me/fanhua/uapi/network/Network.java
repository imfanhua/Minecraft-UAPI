package me.fanhua.uapi.network;

import io.netty.channel.Channel;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.bukkit.entity.Player;

import me.fanhua.uapi.network.packet.IPacket;
import me.fanhua.uapi.utils.ClassUtils;
import me.fanhua.uapi.utils.EntityUtils;

public class Network {
	
	private static Method methodSendPacket;
	
	private static Field fieldPlayerConnection;
	private static Field fieldNetworkManager;
	private static Field fieldChannel;
	
	static {
		try {
			Network.methodSendPacket = ClassUtils.getMethod(ClassUtils.getServerClass("PlayerConnection"), "sendPacket", true, ClassUtils.getServerClass("Packet"));
			
			Network.fieldPlayerConnection = ClassUtils.getField(ClassUtils.getServerClass("EntityPlayer"), "playerConnection", false);
			Network.fieldNetworkManager = ClassUtils.getField(ClassUtils.getServerClass("PlayerConnection"), "networkManager", false);
			Network.fieldChannel = ClassUtils.getField(ClassUtils.getServerClass("NetworkManager"), "k", false);
		} catch (Throwable error) {}
	}
	
	public static Object getPlayerConnection(Player player) {
		try {
			return Network.fieldPlayerConnection.get(EntityUtils.getOriginalEntity(player));
		} catch (Throwable error) {
			return null;
		}
	}
	
	public static Object getNetworkManager(Player player) {
		try {
			return Network.fieldNetworkManager.get(Network.getPlayerConnection(player));
		} catch (Throwable error) {
			return null;
		}
	}
	
	public static boolean send(Player player, IPacket packet) {
		return Network.send(player, packet.getPacketObject());
	}
	
	public static boolean send(Player player, Object packet) {
		try {
			Network.methodSendPacket.invoke(Network.getPlayerConnection(player), packet);
			return true;
		} catch (Throwable error) {
			return false;
		}
	}
	
	public static boolean injector(Player player) {
		try {
			Object manager = Network.getNetworkManager(player);
			Channel channel = (Channel) Network.fieldChannel.get(manager);
			if (channel.pipeline().get("_decoder") != null) return true;
			Network.fieldChannel.set(manager, new NetworkInjector(player, channel).init());
			return true;
		} catch (Throwable error) {
			return false;
		}
	}
	
}