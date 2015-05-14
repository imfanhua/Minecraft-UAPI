package me.fanhua.uapi.rule;

import me.fanhua.uapi.UAPI;
import me.fanhua.uapi.api.API;
import me.fanhua.uapi.gui.Gui;
import me.fanhua.uapi.listener.GuiListener;
import me.fanhua.uapi.manager.EventManager;
import me.fanhua.uapi.manager.RuleManager;
import me.fanhua.uapi.manager.UserManager;
import me.fanhua.uapi.task.TaskGuiTick;
import me.fanhua.uapi.user.User;
import me.fanhua.uapi.user.manager.UserGuiManager;

public class RuleUserGui implements IRule {
	
	public static final RuleUserGui RULE = new RuleUserGui();
	
	private RuleUserGui() {}
	
	@Override
	@Deprecated
	public boolean add(RuleManager manager) {
		manager.addRule(RuleUser.RULE);
		
		UserManager.addRegister(UserGuiManager.REGISTER);
		
		TaskGuiTick.addTask();
		EventManager.addListener(UAPI.getInstance(), new GuiListener());
		return true;
	}
	
	@Override
	@Deprecated
	public boolean remove(RuleManager manager) {
		return false;
	}
	
	@Override
	@Deprecated
	public void disable(RuleManager manager) {
		for (User user : API.getUsers()) {
			Gui gui = user.get(API.USER.GUI).getGui();
			if (gui == null) continue;
			gui.close();
		}
	}

}
