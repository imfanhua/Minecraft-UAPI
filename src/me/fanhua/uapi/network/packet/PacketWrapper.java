package me.fanhua.uapi.network.packet;

import java.lang.reflect.Array;

public class PacketWrapper implements IPacket {
	
	private static final String[] NAME_LIST = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z" };
	
	private Packet packet;
	
	private int write;
	private int read;
	
	public PacketWrapper(PacketType type, String name) {
		this.packet = Packet.create(type, name);
	}
	
	public PacketWrapper(Packet packet) {
		this.packet = packet;
	}
	
	public Packet getPacket() {
		return this.packet;
	}
	
	public PacketWrapper write(Object... array) {
		for (Object data : array) this.packet.set(PacketWrapper.NAME_LIST[this.write++], data);
		return this;
	}
	
	public Object read() {
		return this.packet.get(PacketWrapper.NAME_LIST[this.read++]);
	}
	
	public Object[] read(int size) {
		Object[] data = new Object[size];
		for (int i = 0; i < size; i++) data[i] = this.read();
		return data;
	}
	
	public <T> T read(Class<T> type) {
		return (T) this.read();
	}
	
	public <T> T[] read(Class<T> type, int size) {
		T[] data = (T[]) Array.newInstance(type, size);
		for (int i = 0; i < size; i++) data[i] = this.read(type);
		return data;
	}
	
	public boolean set(String name, Object data) {
		return this.packet.set(name, data);
	}
	
	public boolean set(String name, Object data, boolean declared) {
		return this.packet.set(name, data, declared);
	}

	@Override
	public Object get(String name) {
		return this.packet.get(name);
	}

	@Override
	public Object get(String name, boolean declared) {
		return this.packet.get(name, declared);
	}

	@Override
	public Class getPacketClass() {
		return this.packet.getPacketClass();
	}

	@Override
	public Object getPacketObject() {
		return this.packet.getPacketObject();
	}

	@Override
	public PacketType getType() {
		return this.packet.getType();
	}

	@Override
	public String getName() {
		return this.packet.getName();
	}

	@Override
	public String getPacketName() {
		return this.packet.getPacketName();
	}

	@Override
	public boolean isPacket(String name) {
		return this.packet.isPacket(name);
	}

	@Override
	public boolean isPacket(PacketType type, String name) {
		return this.packet.isPacket(type, name);
	}
	
}
