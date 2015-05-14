package me.fanhua.uapi.rule;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import me.fanhua.uapi.manager.RuleManager;

public final class RuleReloadKick implements IRule {
	
	public static final RuleReloadKick RULE = new RuleReloadKick();
	
	private String reason;
	
	private RuleReloadKick() {}
	
	public boolean isNeedKick() {
		return this.reason != null;
	}
	
	public String getReason() {
		return this.reason;
	}
	
	public void setReason(String reason) {
		this.reason = reason;
	}
	
	public void clearReason() {
		this.reason = null;
	}
	
	@Override
	@Deprecated
	public boolean add(RuleManager manager) {
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
		if (this.reason == null) return;
		for (Player player : Bukkit.getOnlinePlayers()) player.kickPlayer(this.reason);
		
		this.reason = null;
	}

}
