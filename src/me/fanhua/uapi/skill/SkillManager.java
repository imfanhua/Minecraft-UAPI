package me.fanhua.uapi.skill;

import java.util.ArrayList;
import java.util.List;

public class SkillManager {
	
	private List<Skill> list;
	
	public SkillManager() {
		this.list = new ArrayList<Skill>();
	}
	
	public List<Skill> getSkills() {
		return this.list;
	}
	
	public boolean hasSkill(Skill skill) {
		return this.list.contains(skill);
	}
	
	public Skill addSkill(Skill skill) {
		this.list.add(skill);
		return skill;
	}
	
	public void removeSkill(Skill skill) {
		if (skill == null) return;
		this.list.remove(skill);
	}
	
	public void clear() {
		this.list.clear();
	}
	
}
