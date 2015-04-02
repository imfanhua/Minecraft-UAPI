package me.fanhua.uapi.skill.render;

import me.fanhua.uapi.skill.Skill;

public abstract class SkillRender {
	
	private Skill skill;
	
	public SkillRender(Skill skill) {
		this.skill = skill;
	}
	
	public Skill getSkill() {
		return this.skill;
	}
	
	public abstract void render();
	public abstract void remove();
	
}
