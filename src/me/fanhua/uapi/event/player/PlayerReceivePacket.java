package me.fanhua.uapi.event.player;

import me.fanhua.uapi.network.packet.Packet;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;

public class PlayerReceivePacket extends PlayerEvent implements Cancellable {
	
	private static final HandlerList handlers = new HandlerList();
	
	@Override
	public HandlerList getHandlers() {
		return PlayerReceivePacket.handlers;
	}
	
	public static HandlerList getHandlerList() {
		return PlayerReceivePacket.handlers;
	}
	
	private Packet packet;
	
	public PlayerReceivePacket(Player who, Object packet) {
		super(who);
		
		this.packet = Packet.create(packet);
	}
	
	public Packet getPacket() {
		return this.packet;
	}
	
	private boolean cancelled;

	@Override
	public boolean isCancelled() {
		return this.cancelled;
	}

	@Override
	public void setCancelled(boolean cancelled) {
		this.cancelled = cancelled;
	}
	
}
