package me.fanhua.uapi.skill.render;

import me.fanhua.uapi.skill.Skill;

public abstract class SkillRender {
	
	private Skill skill;
	
	public SkillRender(Skill skill) {
		this.skill = skill;
	}
	
	public final Skill getSkill() {
		return this.skill;
	}
	
	public abstract void draw();
	public abstract void remove();
	
}
