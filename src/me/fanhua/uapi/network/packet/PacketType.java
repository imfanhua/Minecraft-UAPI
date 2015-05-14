package me.fanhua.uapi.network.packet;

public enum PacketType {
	
	IN("In"), OUT("Out");
	
	private String name;
	private int size;
	
	private PacketType(String name) {
		this.name = "PacketPlay" + name;
		this.size = name.length();
	}
	
	public String getName(String name) {
		return name.substring(this.size);
	}
	
	public String getPacketName(String name) {
		return this.name + name;
	}
	
	public static PacketType getType(String name) {
		for (PacketType type : PacketType.values()) if (name.startsWith(type.name)) return type;
		return null;
	}
	
}
