package me.fanhua.uapi.network.packet;

public interface IPacket {
	
	public Object get(String name);
	public Object get(String name, boolean declared);
	
	public Class getPacketClass();
	public Object getPacketObject();
	
	public PacketType getType();
	
	public String getName();
	public String getPacketName();
	
	public boolean isPacket(String name);
	public boolean isPacket(PacketType type, String name);
	
}
