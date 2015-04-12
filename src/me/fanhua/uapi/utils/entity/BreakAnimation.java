package me.fanhua.uapi.utils.entity;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import me.fanhua.uapi.network.Packet;
import me.fanhua.uapi.user.User;
import me.fanhua.uapi.utils.EntityUtils;
import me.fanhua.uapi.utils.PacketUtils;

public class BreakAnimation {
	
	private static int getProgress(float progress) {
		return (int) progress * 9;
	}
	
	private int id;
	
	public BreakAnimation() {
		this.id = EntityUtils.newEntityId();
	}
	
	public int getEntityId() {
		return this.id;
	}
	
	private Packet newPacket(Location location, int progress) {
		Packet packet = new Packet("PlayOutBlockBreakAnimation");
		packet.set("a", this.id, true);
		packet.set("b", PacketUtils.newBlockPosition(location.getBlockX(), location.getBlockY(), location.getBlockZ()), true);
		packet.set("c", progress, true);
		return packet;
	}
	
	public void play(Location location, float progress) {
		this.play(location, BreakAnimation.getProgress(progress));
	}
	
	public void play(Location location, int progress) {
		Packet packet = this.newPacket(location, progress);
		for (Player player : location.getWorld().getPlayers()) User.toUser(player).send(packet);
	}
	
	public void play(Player player, Location location, float progress) {
		this.play(User.toUser(player), location, BreakAnimation.getProgress(progress));
	}
	
	public void play(Player player, Location location, int progress) {
		this.play(User.toUser(player), location, progress);
	}
	
	public void play(User user, Location location, float progress) {
		this.play(user, location, BreakAnimation.getProgress(progress));
	}
	
	public void play(User user, Location location, int progress) {
		user.send(this.newPacket(location, progress));
	}
	
}
