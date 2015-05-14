package me.fanhua.uapi.utils.particle;

import me.fanhua.uapi.api.API;
import me.fanhua.uapi.network.packet.IPacket;
import me.fanhua.uapi.user.User;
import me.fanhua.uapi.utils.PacketUtils;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class ParticlesEffect {
	
	private IPacket packet;
	
	private Particles particles;
	
	private float x;
	private float y;
	private float z;
	
	public ParticlesEffect(Particles particles, float x, float y, float z) {
		this.particles = particles.clone();
		
		this.x = x;
		this.y = y;
		this.z = z;
		
		Particle particle = particles.getParticle();
		
		this.packet = PacketUtils.newParticlesPacket(
				particle.getEnumObject(), particle.isLongDistance(), particle.getArgs(),
				this.x, this.y, this.z,
				particles.getOffsetX(), particles.getOffsetY(), particles.getOffsetZ(),
				particles.getSpeed(), particles.getNumber()
			);
	}
	
	public Particles getParticles() {
		return this.particles.clone();
	}
	
	public float getX() {
		return this.x;
	}
	
	public float getY() {
		return this.y;
	}
	
	public float getZ() {
		return this.z;
	}
	
	public boolean isCanSee(Location location) {
		return this.isCanSee(location.getX(), location.getY(), location.getZ());
	}
	
	public boolean isCanSee(double x, double y, double z) {
		if (Math.abs(y - this.y) > 16) return false;
		double distance = Math.abs(x - this.x) + Math.abs(z - this.z);
		return distance < 32;
	}
	
	public void play(Player player) {
		this.play(API.to(player));
	}
	
	public void play(User user) {
		if (!user.isOffline() && this.isCanSee(user.getLocation())) user.send(this.packet);
	}
	
	public void play(World world) {
		String name = world.getName();
		for (Player player : world.getPlayers()) if (name.equals(player.getLocation().getWorld().getName())) this.play(API.to(player));
	}
	
	public ParticlesEffect clone() {
		return new ParticlesEffect(this.particles, this.x, this.y, this.z);
	}
	
}
