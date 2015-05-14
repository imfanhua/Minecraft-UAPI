package me.fanhua.uapi.entity;

import org.bukkit.Location;

import me.fanhua.uapi.api.API;
import me.fanhua.uapi.manager.UEntityManager;
import me.fanhua.uapi.user.IUserFilter;
import me.fanhua.uapi.user.User;
import me.fanhua.uapi.utils.EntityUtils;

public abstract class UEntity {
	
	public static boolean isCanSee(Location entity, float height, float width, Location location) {
		if (!entity.getWorld().getName().equals(location.getWorld().getName())) return false;
		
		if (Math.abs(location.getY() - entity.getY()) > height) return false;
		double distance = Math.abs(location.getX() - entity.getX()) + Math.abs(location.getZ() - entity.getZ());
		return distance < width;
	}
	
	protected final int id;
	protected Location location;
	protected IUserFilter filter;
	
	public UEntity(Location location) {
		this.id = EntityUtils.newEntityId();
		this.location = location.clone();
	}
	
	public int getId() {
		return this.id;
	}
	
	public void teleport(Location location) {
		this.refresh(true);
		
		this.location = location.clone();
		
		this.refresh(false);
		this.move();
	}
	
	public Location getLocation() {
		return this.location.clone();
	}
	
	protected void move() {}
	
	protected abstract void spawn(User user);
	protected abstract void despawn(User user);
	
	protected abstract void spawn();
	protected abstract void remove();
	
	public abstract void tick(int tick);
	
	public boolean isCanSee(User user, boolean dead) {
		return this.isCanSee(user, user.getLocation(), dead);
	}
	
	public boolean isCanSee(User user, Location location, boolean dead) {
		if (dead) if (user.isDead()) return false;
		if (!this.isCanSee(location)) return false;
		if (this.filter == null) return true;
		return !this.filter.isSkip(user);
	}
	
	public boolean isCanSee(Location location) {
		return UEntity.isCanSee(this.location, this.getCanSeeHiehgt(), this.getCanSeeDistance(), location);
	}
	
	protected float getCanSeeHiehgt() {
		return 64;
	}
	
	protected float getCanSeeDistance() {
		return 256;
	}
	
	protected void refresh(boolean despawn) {
		if (despawn) {
			for (User user : API.getUsers()) if (this.isCanSee(user, false)) this.despawn(user);
		} else for (User user : API.getUsers()) if (this.isCanSee(user, true)) this.spawn(user);
	}
	
	public void despawn() {
		UEntityManager.getInstance().remove(this);
	}
	
	@Deprecated
	public void onMove(User user, Location location, Location to) {
		if (user.isDead()) {
			this.despawn(user);
			return;
		}
		
		boolean old = location != null ? this.isCanSee(location) : false;
		boolean now = this.isCanSee(to);
		
		if (old) {
			if (!now) this.despawn(user);
		} else {
			if (now) this.spawn(user);
		}
	}
	
	@Deprecated
	public void onSpawn() {
		this.spawn();
		for (User user : API.getUsers()) if (this.isCanSee(user, true)) this.spawn(user);
	}
	
	@Deprecated
	public void onRemove() {
		this.remove();
		for (User user : API.getUsers()) if (this.isCanSee(user, false)) this.despawn(user);
	}
	
}
