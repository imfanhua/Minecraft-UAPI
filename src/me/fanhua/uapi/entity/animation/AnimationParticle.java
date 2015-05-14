package me.fanhua.uapi.entity.animation;

import org.bukkit.Location;

import me.fanhua.uapi.entity.UEntity;
import me.fanhua.uapi.user.User;
import me.fanhua.uapi.utils.particle.Particles;

public class AnimationParticle implements IAnimation {
	
	private Particles particles;
	private int tick;
	
	public AnimationParticle(Particles particles, int tick) {
		this.particles = particles.clone();
		this.tick = tick;
	}
	
	public Particles getParticles() {
		return this.particles.clone();
	}
	
	public void setParticles(Particles particles) {
		this.particles = particles.clone();
	}
	
	public int getTick() {
		return this.tick;
	}
	
	public void setTick(int tick) {
		this.tick = tick;
	}
	
	public void addTick(int tick) {
		this.tick += tick;
	}
	
	@Override
	@Deprecated
	public void spawn(UEntity entity, User user) {}

	@Override
	@Deprecated
	public void despawn(UEntity entity, User user) {}

	@Override
	@Deprecated
	public float getCanSeeHiehgt() {
		return -100;
	}

	@Override
	@Deprecated
	public float getCanSeeDistance() {
		return -100;
	}
	
	@Override
	@Deprecated
	public void move(UEntity entity) {}
	
	@Override
	@Deprecated
	public boolean tick(UEntity entity, int tick) {
		if (tick % this.tick != 0) return false;
		Location location = entity.getLocation();
		this.particles.newEffect(location).play(location.getWorld());
		return false;
	}
	
}
