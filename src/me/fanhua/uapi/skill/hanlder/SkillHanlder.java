package me.fanhua.uapi.skill.hanlder;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import me.fanhua.uapi.skill.Skill;
import me.fanhua.uapi.skill.event.SkillEvent;

public abstract class SkillHanlder {
	
	private static Map<String, Map<String, Method>> events = new HashMap<String, Map<String, Method>>();
	
	private static Method getEventMethod(Class<? extends SkillHanlder> clazz, Class<? extends SkillEvent> event) {
		String name = clazz.getName();
		Map<String, Method> map = SkillHanlder.events.get(name);
		if (map == null) {
			map = new HashMap<String, Method>();
			SkillHanlder.events.put(name, map);
		}
		
		name = event.getName();
		if (!map.containsKey(name)) {
			for (Method method : clazz.getMethods()) {
				Class<?>[] args = method.getParameterTypes();
				if (args.length == 1 && args[0].getName().equals(name)) {
					method.setAccessible(true);
					map.put(name, method);
					return method;
				}
			}
			
			map.put(name, null);
			return null;
		} else {
			return map.get(name);
		}
	}
	
	private Skill skill;
	
	public SkillHanlder(Skill skill) {
		this.skill = skill;
	}
	
	public Skill getSkill() {
		return this.skill;
	}
	
	public void onEvent(SkillEvent event) {
		Method method = SkillHanlder.getEventMethod(this.getClass(), event.getClass());
		if (method == null) return;
		
		try {
			method.invoke(this, event);
		} catch (Throwable error) {
			error.printStackTrace();
		}
	}
	
}
