package me.fanhua.uapi.skill.hanlder;

import me.fanhua.uapi.event.base.bus.UBus;
import me.fanhua.uapi.skill.Skill;
import me.fanhua.uapi.skill.event.SkillEvent;

public abstract class SkillHanlder {
	
	private Skill skill;
	
	private UBus bus;
	
	public SkillHanlder(Skill skill) {
		this.skill = skill;
		
		this.bus = new UBus();
		this.bus.register(this);
	}
	
	public final Skill getSkill() {
		return this.skill;
	}
	
	public final void call(SkillEvent event) {
		UBus.report(this.bus.call(event));
	}
	
}
