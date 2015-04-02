package me.fanhua.uapi.skill.render;

import me.fanhua.uapi.skill.Skill;

import org.bukkit.inventory.ItemStack;

public class SkillRenderNone extends SkillRender {
	
	public SkillRenderNone(Skill skill, ItemStack item) {
		super(skill);
	}
	
	@Override
	public void render() {}
	
	@Override
	public void remove() {}
	
}
