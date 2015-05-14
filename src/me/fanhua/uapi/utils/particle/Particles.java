package me.fanhua.uapi.utils.particle;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.util.Vector;

public class Particles {
	
	public static Particles create(Particle particle, Color color) {
		return Particles.create(particle, color.getRed() / 255.0F, color.getGreen() / 255.0F, color.getBlue() / 255.0F, 1.0F, 0);
	}
	
	public static Particles create(Particle particle, Vector offset, float speed, int number) {
		return Particles.create(particle, (float) offset.getX(), (float) offset.getY(), (float) offset.getZ(), speed, number);
	}
	
	public static Particles create(Particle particle, float offsetX, float offsetY, float offsetZ, float speed, int number) {
		return new Particles(particle, offsetX, offsetY, offsetZ, speed, number);
	}
	
	private Particle particle;
	
	private float offsetX;
	private float offsetY;
	private float offsetZ;
	
	private float speed;
	private int number;
	
	private Particles(Particle particle, float offsetX, float offsetY, float offsetZ, float speed, int number) {
		this.particle = particle.clone();
		
		this.offsetX = offsetX;
		this.offsetY = offsetY;
		this.offsetZ = offsetZ;
		
		this.speed = speed;
		this.number = number;
	}
	
	public Particle getParticle() {
		return this.particle.clone();
	}
	
	public void setParticle(Particle particle) {
		this.particle = particle.clone();
	}

	public float getOffsetX() {
		return this.offsetX;
	}
	
	public float getOffsetY() {
		return this.offsetY;
	}
	
	public float getOffsetZ() {
		return this.offsetZ;
	}
	
	public Vector getOffset() {
		return new Vector(this.offsetX, this.offsetY, this.offsetZ);
	}
	
	public void setOffsetX(float offsetX) {
		this.offsetX = offsetX;
	}
	
	public void setOffsetY(float offsetY) {
		this.offsetY = offsetY;
	}
	
	public void setOffsetZ(float offsetZ) {
		this.offsetZ = offsetZ;
	}
	
	public void addOffset(float offsetX, float offsetY, float offsetZ) {
		this.offsetX += offsetX;
		this.offsetY += offsetY;
		this.offsetZ += offsetZ;
	}
	
	public void setOffset(float offsetX, float offsetY, float offsetZ) {
		this.offsetX = offsetX;
		this.offsetY = offsetY;
		this.offsetZ = offsetZ;
	}
	
	public void setOffset(Vector offset) {
		this.offsetX = (float) offset.getX();
		this.offsetY = (float) offset.getY();
		this.offsetZ = (float) offset.getZ();
	}
	
	public float getSpeed() {
		return this.speed;
	}
	
	public void addSpeed(float speed) {
		this.speed += speed;
	}
	
	public void setSpeed(float speed) {
		this.speed = speed;
	}
	
	public int getNumber() {
		return this.number;
	}
	
	public void addNumber(int number) {
		this.number += number;
	}
	
	public void setNumber(int number) {
		this.number = number;
	}
	
	public ParticlesEffect newEffect(float x, float y, float z) {
		return new ParticlesEffect(this, x, y, z);
	}
	
	public ParticlesEffect newEffect(Location location) {
		return this.newEffect((float) location.getX(), (float) location.getY(), (float) location.getZ());
	}
	
	public Particles clone() {
		return new Particles(this.particle, this.offsetX, this.offsetY, this.offsetZ, this.speed, this.number);
	}
	
}
