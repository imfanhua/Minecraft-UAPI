package me.fanhua.uapi.utils.particle;

import me.fanhua.uapi.network.Packet;
import me.fanhua.uapi.user.User;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class ParticleEffect {
	
	public static ParticleEffect create(Particle particle, Location location, Vector offset, float speed, int number) {
		return ParticleEffect.create(particle,
				(float) location.getX(), (float) location.getY(), (float) location.getZ(),
				(float) offset.getX(), (float) offset.getY(), (float) offset.getZ(),
				speed, number);
	}
	
	public static ParticleEffect create(Particle particle, Location location, float offsetX, float offsetY, float offsetZ, float speed, int number) {
		return ParticleEffect.create(particle,
				(float) location.getX(), (float) location.getY(), (float) location.getZ(),
				offsetX, offsetY, offsetZ, speed, number);
	}
	
	public static ParticleEffect create(Particle particle, float x, float y, float z, Vector offset, float speed, int number) {
		return ParticleEffect.create(particle, x, y, z,
				(float) offset.getX(), (float) offset.getY(), (float) offset.getZ(),
				speed, number);
	}
	
	public static ParticleEffect create(Particle particle, float x, float y, float z, float offsetX, float offsetY, float offsetZ, float speed, int number) {
		Packet packet = new Packet("PlayOutWorldParticles");
		packet.set("a", particle.getEnumObject(), true);
		packet.set("j", particle.isForce(), true);
		
		packet.set("b", x, true);
		packet.set("c", y, true);
		packet.set("d", z, true);
		
		packet.set("e", offsetX, true);
		packet.set("f", offsetY, true);
		packet.set("g", offsetZ, true);
		
		packet.set("h", speed, true);
		packet.set("i", number, true);
		packet.set("k", particle.getArgs(), true);
		
		return new ParticleEffect(packet, (int) x, (int) y, (int) z);
	}
	
	private Packet packet;
	
	private int x;
	private int y;
	private int z;
	
	private ParticleEffect(Packet packet, int x, int y, int z) {
		this.packet = packet;
		
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public boolean canSee(Location location) {
		return this.canSee(location.getBlockX(), location.getBlockY(), location.getBlockZ());
	}
	
	public boolean canSee(int x, int y, int z) {
		int distance = Math.abs(x - this.x) + Math.abs(y - this.y) + Math.abs(z - this.z);
		return distance <= 256;
	}
	
	public void play(User user) {
		user.play(this);
	}
	
	public void play(World world) {
		for (Player player : world.getPlayers()) User.toUser(player).play(this);
	}
	
	public Packet getPacket() {
		return this.packet;
	}
	
}
