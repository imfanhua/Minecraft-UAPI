package me.fanhua.uapi.network;

import java.util.HashMap;
import java.util.Map;

import me.fanhua.uapi.utils.ClassUtils;

public final class Packet {
	
	private final static Map<String, Class> map = new HashMap<String, Class>();
	
	private Class target;
	private Object object;
	
	
	public Packet(String name) {
		this.target = Packet.map.get(name);
		if (this.target == null) {
			this.target = ClassUtils.getServerClass("Packet" + name);
			Packet.map.put(name, this.target);
		}
		
		try {
			this.object = this.target.newInstance();
		} catch (Throwable error) {}
	}
	
	public Class getPacketClass() {
		return this.target;
	}
	
	public Object getPacketObject() {
		return this.object;
	}
	
	public Packet set(String name, Object data, boolean declared) {
		try {
			ClassUtils.getField(this.target, name, declared).set(this.object, data);
			return this;
		} catch (Throwable error) {
			return null;
		}
	}
	
	public Object get(String name, boolean declared) {
		try {
			return ClassUtils.getField(this.target, name, declared).get(this.object);
		} catch (Throwable error) {
			return null;
		}
	}
	
}
