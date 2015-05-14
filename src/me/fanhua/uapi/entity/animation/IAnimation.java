package me.fanhua.uapi.entity.animation;

import me.fanhua.uapi.entity.UEntity;
import me.fanhua.uapi.user.User;

public interface IAnimation {
	
	@Deprecated
	public void spawn(UEntity entity, User user);
	@Deprecated
	public void despawn(UEntity entity, User user);
	
	@Deprecated
	public float getCanSeeHiehgt();
	@Deprecated
	public float getCanSeeDistance();
	
	@Deprecated
	public void move(UEntity entity);
	@Deprecated
	public boolean tick(UEntity entity, int tick);
	
}
