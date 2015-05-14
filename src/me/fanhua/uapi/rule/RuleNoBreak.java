package me.fanhua.uapi.rule;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;

import me.fanhua.uapi.UAPI;
import me.fanhua.uapi.block.BlockRule;
import me.fanhua.uapi.listener.BreakListener;
import me.fanhua.uapi.manager.EventManager;
import me.fanhua.uapi.manager.RuleManager;

public final class RuleNoBreak implements IRule {
	
	public static final RuleNoBreak RULE = new RuleNoBreak();
	
	private boolean global;
	private List<String> worlds;
	private List<BlockRule> blocks;
	
	private RuleNoBreak() {
		this.worlds = new ArrayList<String>();
		this.blocks = new ArrayList<BlockRule>();
		
		this.addPhysicalBlocks();
	}
	
	public boolean isGlobal() {
		return this.global;
	}
	
	public String[] setToGlobal() {
		this.global = true;
		return this.clear();
	}
	
	public boolean hasWorld(World world) {
		if (this.global) return true;
		return this.worlds.contains(world.getName());
	}
	
	public boolean hasWorld(String world) {
		if (this.global) return true;
		return this.worlds.contains(world.toLowerCase());
	}
	
	public void addWorld(World world) {
		if (this.global || this.hasWorld(world)) return;
		this.worlds.add(world.getName());
	}
	
	public void addWorld(String world) {
		if (this.global || this.hasWorld(world)) return;
		this.worlds.add(world);
	}
	
	public void addWorlds(String... worlds) {
		if (this.global) return;
		for (String world : worlds) this.addWorld(world);
	}
	
	public void addWorlds(World... worlds) {
		if (this.global) return;
		for (World world : worlds) this.addWorld(world);
	}
	
	public void addWorlds(Collection<World> worlds) {
		if (this.global) return;
		for (World world : worlds) this.addWorld(world);
	}
	
	public void removeWorld(World world) {
		if (this.global) return;
		this.worlds.remove(world.getName());
	}
	
	public void removeWorld(String world) {
		if (this.global) return;
		this.worlds.remove(world);
	}
	
	public void removeWorlds(String... worlds) {
		if (this.global) return;
		for (String world : worlds) this.removeWorld(world);
	}
	
	public void removeWorlds(World... worlds) {
		if (this.global) return;
		for (World world : worlds) this.removeWorld(world);
	}
	
	public void removeWorlds(Collection<World> worlds) {
		if (this.global) return;
		for (World world : worlds) this.removeWorld(world);
	}
	
	public int size() {
		return this.worlds.size();
	}
	
	public String getCommand(int index) {
		return this.worlds.get(index);
	}
	
	public String[] getWorlds() {
		return this.worlds.toArray(new String[this.worlds.size()]);
	}
	
	public String[] clear() {
		String[] worlds = this.getWorlds();
		this.worlds.clear();
		return worlds;
	}
	
	private void addPhysicalBlocks() {
		this.blocks.add(new BlockRule(70));
		this.blocks.add(new BlockRule(72));
		this.blocks.add(new BlockRule(77));
		this.blocks.add(new BlockRule(143));
		this.blocks.add(new BlockRule(69));
		this.blocks.add(new BlockRule(147));
		this.blocks.add(new BlockRule(148));
	}
	
	public List<BlockRule> getPhysicalBlocks() {
		return this.blocks;
	}
	
	public boolean check(World world) {
		return this.hasWorld(world);
	}
	
	public boolean check(Entity entity) {
		return this.check(entity.getWorld());
	}
	
	public boolean check(Location location) {
		return this.check(location.getWorld());
	}
	
	public boolean check(Block block) {
		for (BlockRule rule : this.blocks) if (rule.check(block)) return true;
		return false;
	}
	
	@Override
	@Deprecated
	public boolean add(RuleManager manager) {
		EventManager.addListener(UAPI.getInstance(), new BreakListener());
		return true;
	}

	@Override
	@Deprecated
	public boolean remove(RuleManager manager) {
		return false;
	}

	@Override
	public void disable(RuleManager manager) {
		this.worlds.clear();
		this.global = false;
		
		this.blocks.clear();
		this.addPhysicalBlocks();
	}

}
