package me.fanhua.uapi.rule;

import me.fanhua.uapi.UAPI;
import me.fanhua.uapi.listener.PacketListener;
import me.fanhua.uapi.manager.EventManager;
import me.fanhua.uapi.manager.RuleManager;

public class RuleUserHookPacket implements IRule {
	
	public static final RuleUserHookPacket RULE = new RuleUserHookPacket();
	
	private RuleUserHookPacket() {}
	
	@Override
	@Deprecated
	public boolean add(RuleManager manager) {
		manager.addRule(RuleUser.RULE);
		
		EventManager.addListener(UAPI.getInstance(), new PacketListener());
		return true;
	}
	
	@Override
	@Deprecated
	public boolean remove(RuleManager manager) {
		return false;
	}
	
	@Override
	@Deprecated
	public void disable(RuleManager manager) {}

}
