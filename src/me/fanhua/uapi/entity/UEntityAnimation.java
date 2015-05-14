package me.fanhua.uapi.entity;

import org.bukkit.Location;

import me.fanhua.uapi.entity.animation.IAnimation;
import me.fanhua.uapi.user.User;

public class UEntityAnimation extends UEntity {
	
	private IAnimation animation;
	
	public UEntityAnimation(Location location, IAnimation animation) {
		super(location);
		this.setAnimation(animation);
	}
	
	public IAnimation getAnimation() {
		return this.animation;
	}
	
	public void setAnimation(IAnimation animation) {
		if (this.animation != null) this.refresh(true);
		
		this.animation = animation;
		this.animation.move(this);
		
		this.refresh(false);
	}
	
	@Override
	protected float getCanSeeHiehgt() {
		return this.animation.getCanSeeDistance();
	}
	
	@Override
	protected float getCanSeeDistance() {
		return this.animation.getCanSeeDistance();
	}
	
	@Override
	protected void spawn(User user) {
		this.animation.spawn(this, user);
	}
	
	@Override
	protected void despawn(User user) {
		this.animation.despawn(this, user);
	}
	
	@Override
	protected void spawn() {}
	
	@Override
	protected void remove() {}
	
	@Override
	public void move() {
		super.move();
		this.animation.move(this);
	}
	
	@Override
	public void tick(int tick) {
		if (this.animation.tick(this, tick)) this.refresh(false);;
	}
	
}
