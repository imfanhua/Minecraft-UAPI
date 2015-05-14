package me.fanhua.uapi.network.packet;

import java.util.HashMap;
import java.util.Map;

import me.fanhua.uapi.utils.ClassUtils;

public final class Packet implements IPacket {
	
	public static final PacketType IN = PacketType.IN;
	public static final PacketType OUT = PacketType.OUT;
	
	private final static Map<String, Class> map = new HashMap<String, Class>();
	
	public static PacketWrapper wrapper(String name) {
		return Packet.create(name).toWrapper();
	}
	
	public static PacketWrapper wrapper(PacketType type, String name) {
		return Packet.create(type, name).toWrapper();
	}
	
	public static PacketWrapper wrapper(Packet packet) {
		return packet.toWrapper();
	}
	
	public static Packet create(String name) {
		PacketType type = PacketType.getType(name);
		return Packet.create(type, type.getName(name));
	}
	
	public static Packet create(PacketType type, String name) {
		name = type.getPacketName(name);
		Class<?> target = Packet.map.get(name);
		if (target == null) {
			target = ClassUtils.getServerClass(name);
			Packet.map.put(name, target);
		}
		
		try {
			return new Packet(type, name, target.newInstance());
		} catch (Throwable error) {
			return null;
		}
	}
	
	public static Packet create(Object object) {
		return new Packet(object);
	}
	
	private PacketType type;
	
	private String name;
	private String packet;
	
	private Class target;
	private Object object;
	
	private Packet(Object object) {
		this.object = object;
		this.target = object.getClass();
		
		this.packet = this.target.getSimpleName();
		this.type = PacketType.getType(this.packet);
		this.name = this.type.getName(this.packet);
	}
	
	private Packet(PacketType type, String name, Object object) {
		this.object = object;
		this.target = object.getClass();
		
		this.type = type;
		this.name = name;
		this.packet = this.target.getSimpleName();
	}
	
	public PacketWrapper toWrapper() {
		return new PacketWrapper(this);
	}
	
	public boolean set(String name, Object data) {
		try {
			ClassUtils.getField(this.target, name, true).set(this.object, data);
			return true;
		} catch (Throwable error) {
			return this.set(name, data, false);
		}
	}
	
	public boolean set(String name, Object data, boolean declared) {
		try {
			ClassUtils.getField(this.target, name, declared).set(this.object, data);
			return true;
		} catch (Throwable error) {
			return false;
		}
	}
	
	@Override
	public Object get(String name) {
		try {
			return ClassUtils.getField(this.target, name, true).get(this.object);
		} catch (Throwable error) {
			return this.get(name, false);
		}
	}
	
	@Override
	public Object get(String name, boolean declared) {
		try {
			return ClassUtils.getField(this.target, name, declared).get(this.object);
		} catch (Throwable error) {
			return null;
		}
	}
	
	@Override
	public Class getPacketClass() {
		return this.target;
	}
	
	@Override
	public Object getPacketObject() {
		return this.object;
	}
	
	@Override
	public PacketType getType() {
		return this.type;
	}
	
	@Override
	public String getName() {
		return this.name;
	}
	
	@Override
	public String getPacketName() {
		return this.packet;
	}
	
	@Override
	public boolean isPacket(String name) {
		return this.packet.equals(name);
	}

	@Override
	public boolean isPacket(PacketType type, String name) {
		if (this.type != type) return false;
		return this.name.equals(name);
	}
	
}
