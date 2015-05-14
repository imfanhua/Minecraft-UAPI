package me.fanhua.uapi.rule;

import me.fanhua.uapi.UAPI;
import me.fanhua.uapi.listener.UserListener;
import me.fanhua.uapi.manager.EventManager;
import me.fanhua.uapi.manager.RuleManager;
import me.fanhua.uapi.manager.UserManager;
import me.fanhua.uapi.user.manager.UserChatManager;

public final class RuleUser implements IRule {
	
	public static final RuleUser RULE = new RuleUser();
	
	private RuleUser() {}
	
	@Override
	@Deprecated
	public boolean add(RuleManager manager) {
		UserManager.addRegister(UserChatManager.REGISTER);
		EventManager.addListener(UAPI.getInstance(), new UserListener());
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
		UserManager.clear();
		UserManager.clearRegister();
	}

}
