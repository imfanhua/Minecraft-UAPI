package me.fanhua.uapi.rule;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import me.fanhua.uapi.UAPI;
import me.fanhua.uapi.listener.CommandsListener;
import me.fanhua.uapi.manager.EventManager;
import me.fanhua.uapi.manager.RuleManager;

public final class RuleBanCommands implements IRule {
	
	public static final String[] DEFAULTS = new String[] {
		"kill",
		"me",
		"help",
		"?",
		"plugins",
		"pl",
		"tell",
		"msg",
		"m",
		"w",
		"version",
		"about",
		"ver",
		"list"
	};
	
	public static final RuleBanCommands RULE = new RuleBanCommands();
	
	private List<String> commands;
	
	private RuleBanCommands() {
		this.commands = new ArrayList<String>();
		this.addCommands(RuleBanCommands.DEFAULTS);
	}
	
	public boolean hasCommand(String command) {
		return this.commands.contains(command.toLowerCase());
	}
	
	public void addCommand(String command) {
		if (this.hasCommand(command)) return;
		this.commands.add(command.toLowerCase());
	}
	
	public void addCommands(String... commands) {
		for (String command : commands) this.addCommand(command);
	}
	
	public void addCommands(Collection<String> commands) {
		for (String command : commands) this.addCommand(command);
	}
	
	public void removeCommand(String command) {
		this.commands.remove(command.toLowerCase());
	}
	
	public void removeCommands(String... commands) {
		for (String command : commands) this.removeCommand(command);
	}
	
	public void removeCommands(Collection<String> commands) {
		for (String command : commands) this.removeCommand(command);
	}
	
	public int size() {
		return this.commands.size();
	}
	
	public String getCommand(int index) {
		return this.commands.get(index);
	}
	
	public String[] getCommands() {
		return this.commands.toArray(new String[this.commands.size()]);
	}
	
	public String[] clear() {
		String[] commands = this.getCommands();
		this.commands.clear();
		return commands;
	}
	
	public boolean check(String message) {
		message = message.toLowerCase();
		if (message.startsWith("/")) message = message.substring(1);
		
		for (String command : this.commands) if (message.equals(command) || message.startsWith(command + " ")) return true;
		return false;
	}
	
	@Override
	@Deprecated
	public boolean add(RuleManager manager) {
		EventManager.addListener(UAPI.getInstance(), new CommandsListener());
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
		this.commands.clear();
		this.addCommands(RuleBanCommands.DEFAULTS);
	}

}
