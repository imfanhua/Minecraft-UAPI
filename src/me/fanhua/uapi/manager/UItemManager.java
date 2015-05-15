package me.fanhua.uapi.manager;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.inventory.ItemStack;

import me.fanhua.uapi.item.UItem;
import me.fanhua.uapi.nbt.NBT;
import me.fanhua.uapi.nbt.NBTTagString;
import me.fanhua.uapi.rule.RuleUItem;
import me.fanhua.uapi.utils.ItemUtils;

public final class UItemManager {
	
	public static void newInstance() {
		RuleUItem.RULE.setManager(new UItemManager());
	}
	
	public static UItemManager getInstance() {
		return RuleUItem.RULE.getManager();
	}
	
	private Map<String, UItem> items;
	
	private UItemManager() {
		this.items = new HashMap<String, UItem>();
	}
	
	public void register(UItem item) {
		this.items.put(item.getId(), item);
	}
	
	public void unregister(UItem item) {
		this.items.remove(item.getId());
	}
	
	public UItem getItem(String id) {
		return this.items.get(id);
	}
	
	public UItem getItem(ItemStack item) {
		NBTTagString id = ItemUtils.getNBT(item).get("uitem", NBT.STRING);
		if (id == null) return null;
		return this.getItem(id.get());
	}
	
	@Deprecated
	public void clear() {
		this.items.clear();
	}
	
}
