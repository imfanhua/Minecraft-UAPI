package me.fanhua.uapi.skill;

import org.bukkit.entity.Player;

import me.fanhua.uapi.skill.hanlder.SkillHanlder;
import me.fanhua.uapi.skill.render.SkillRender;
import me.fanhua.uapi.user.User;

public abstract class Skill<T extends SkillRender> {
	
	private User user;
	private SkillHanlder hanlder;
	private T render;
	
	private int use;
	private int cd;
	
	public Skill(User user) {
		this.user = user;
		this.cd = 1;
	}
	
	public final User getUser() {
		return this.user;
	}
	
	public final Player getPlayer() {
		return this.user.getPlayer();
	}
	
	public final void setHanlder(SkillHanlder hanlder) {
		this.hanlder = hanlder;
	}
	
	public final SkillHanlder getHanlder() {
		return this.hanlder;
	}
	
	public final void setRender(T render) {
		this.render = render;
	}
	
	public final T getRender() {
		return this.render;
	}
	
	public abstract int getMaxUse();
	public abstract int getSkillCD();
	
	public void onTick() {
		if (this.cd == 0) return;
		if (this.cd != 0) this.cd -= 1;
		if (this.cd == 0) this.onCD();
	}
	
	public void goUse() {
		if (this.cd != 0) return;
		if (this.use < 1) return;
		this.use -= 1;
		if (this.use < 1) {
			this.onUse();
			this.onUseOut();
		} else this.onUse();
		this.render.draw();
	}
	
	public void goCD() {
		if (this.cd != 0) return;
		this.cd = this.getSkillCD();
		this.onGoCD();
		if (this.cd == 0) this.onCD();
	}
	
	public void setCD(int cd) {
		int old = this.cd;
		if (old == cd) return;
		this.cd = cd;
		if (this.cd == 0) this.onCD();
		else if (old == 0) this.onGoCD();
		this.render.draw();
	}
	
	public void setUse(int use) {
		this.use = use;
	}
	
	public int getUse() {
		return this.use;
	}
	
	public int getCD() {
		return this.cd;
	}
	
	public abstract void onUseOut();
	public abstract void onUse();
	public abstract void onGoCD();
	public abstract void onCD();
	
	public void onRemoved() {}
	
	public final void remove() {
		this.render.remove();
		this.onRemoved();
	}
	
}
