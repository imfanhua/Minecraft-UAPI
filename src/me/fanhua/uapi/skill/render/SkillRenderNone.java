package me.fanhua.uapi.skill.render;

import me.fanhua.uapi.skill.Skill;

import org.bukkit.inventory.ItemStack;

public final class SkillRenderNone extends SkillRender {
	
	public SkillRenderNone(Skill skill, ItemStack item) {
		super(skill);
	}
	
	@Override
	public void draw() {}
	
	@Override
	public void remove() {}
	
}
