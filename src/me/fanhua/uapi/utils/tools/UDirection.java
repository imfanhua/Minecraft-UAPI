package me.fanhua.uapi.utils.tools;

import org.bukkit.Location;
import org.bukkit.util.Vector;

public class UDirection {
	
	private float yaw;
	private float pitch;
	
	public UDirection(float yaw, float pitch) {
		this.yaw = yaw;
		this.pitch = pitch;
	}
	
	public UDirection(Location location) {
		this.yaw = location.getYaw();
		this.pitch = location.getPitch();
	}
	
	public UDirection(Vector vector) {
		this.setDirection(vector);
	}
	
	public UDirection(double x, double y, double z) {
		this.setDirection(x, y, z);
	}
	
	public float getYaw() {
		return this.yaw;
	}
	
	public float getPitch() {
		return this.pitch;
	}
	
	public UDirection setYaw(float yaw) {
		this.yaw = yaw;
		return this;
	}
	
	public UDirection setPitch(float pitch) {
		this.pitch = pitch;
		return this;
	}
	
	public UDirection addYaw(float yaw) {
		this.yaw += yaw;
		return this;
	}
	
	public UDirection addPitch(float pitch) {
		this.pitch += pitch;
		return this;
	}
	
	public UDirection setDirection(float yaw, float pitch) {
		this.yaw = yaw;
		this.pitch = pitch;
		return this;
	}
	
	public UDirection setDirection(Vector vector) {
		return this.setDirection(vector.getX(), vector.getY(), vector.getZ());
	}
	
	public UDirection setDirection(double x, double y, double z) {
		if (x == 0 && z == 0) this.pitch = y > 0 ? -90 : 90;
		else {
			double theta = Math.atan2(-x, z);
			double PI = 2 * Math.PI;
			this.yaw = (float) Math.toDegrees((theta + PI) % PI);
			
			double direction = Math.sqrt(x * x + z * z);
			this.pitch = (float) Math.toDegrees(Math.atan(-y / direction));
		}
		
		return this;
	}
	
	public UDirection addDirection(float yaw, float pitch) {
		this.yaw += yaw;
		this.pitch += pitch;
		return this;
	}
	
	public void check() {
		while(true) {
			if (this.yaw > 360) {
				this.yaw -= 360;
				continue;
			}
			
			if (this.yaw < 0) {
				this.yaw += 360;
				continue;
			}
			
			if (this.pitch > 90) {
				this.pitch -= 90;
				continue;
			}
			
			if (this.pitch < -90) {
				this.pitch += 90;
				continue;
			}
		}
	}
	
	public Vector toVector() {
		this.check();
		
		double y = -Math.sin(Math.toRadians(this.pitch));
		double direction = Math.cos(Math.toRadians(this.pitch));
		
		double x = -direction * Math.sin(Math.toRadians(this.yaw));
		double z = direction * Math.cos(Math.toRadians(this.yaw));
		
		return new Vector(x, y, z);
	}
	
	public Location setToLocation(Location location) {
		return location.setDirection(this.toVector());
	}
	
	@Override
	public boolean equals(Object object) {
		if (object == null || object.getClass() != UDirection.class) return false;
		UDirection direction = (UDirection) object;
		return this.yaw == direction.yaw && this.pitch == direction.pitch;
	}
	
	public UDirection clone() {
		return new UDirection(this.yaw, this.pitch);
	}
	
}
