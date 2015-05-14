package me.fanhua.uapi.entity.animation;

import org.bukkit.Location;

import me.fanhua.uapi.entity.UEntity;
import me.fanhua.uapi.network.packet.IPacket;
import me.fanhua.uapi.user.User;
import me.fanhua.uapi.utils.PacketUtils;

public class AnimationBreak implements IAnimation {
	
	private static IPacket newPacket(UEntity entity, int progress) {
		Location location = entity.getLocation();
		return PacketUtils.newBreakAnimationPacket(entity.getId(), location.getBlockX(), location.getBlockY(), location.getBlockZ(), progress < 0 ? 10 : progress);
	}
	
	private static int getProgress(float progress) {
		return (int) (progress * 10) - 1;
	}
	
	private static float getProgress(int progress) {
		return (progress + 1) / 10.0F;
	}
	
	private int progress;
	
	public AnimationBreak(float progress) {
		this(AnimationBreak.getProgress(progress));
	}
	
	public AnimationBreak(int progress) {
		this.progress = progress;
	}
	
	public int getProgress() {
		return this.progress;
	}
	
	public float getFloatProgress() {
		return AnimationBreak.getProgress(this.progress);
	}
	
	public void setProgress(int progress) {
		this.progress = progress;
		if (this.progress < -1) this.progress = -1;
		else if (this.progress > 9) this.progress = 9;
	}
	
	public void setProgress(float progress) {
		this.setProgress(AnimationBreak.getProgress(progress));
	}
	
	public void addProgress(int progress) {
		this.setProgress(this.progress + progress);
	}
	
	public void addProgress(float progress) {
		this.setProgress(AnimationBreak.getProgress(this.progress) + progress);
	}

	@Override
	public void spawn(UEntity entity, User user) {
		user.send(AnimationBreak.newPacket(entity, this.progress));
	}

	@Override
	public void despawn(UEntity entity, User user) {
		user.send(AnimationBreak.newPacket(entity, -1));
	}

	@Override
	public float getCanSeeHiehgt() {
		return 32;
	}

	@Override
	public float getCanSeeDistance() {
		return 24;
	}
	
	@Override
	public void move(UEntity entity) {}
	
	@Override
	public boolean tick(UEntity entity, int tick) {
		return tick % (20 * 5) == 0;
	}
	
}
