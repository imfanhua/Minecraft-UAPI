package me.fanhua.uapi.utils.item.tag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.fanhua.uapi.nbt.NBTTag;
import me.fanhua.uapi.nbt.NBTTagCompound;
import me.fanhua.uapi.nbt.NBTTagInt;
import me.fanhua.uapi.nbt.NBTTagList;
import me.fanhua.uapi.nbt.NBTTagShort;
import me.fanhua.uapi.nbt.NBTTagString;
import me.fanhua.uapi.utils.item.ItemUtils;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

public class ItemTag {
	
	private ItemStack item;
	protected NBTTagCompound tag;
	
	private String title;
	private List<String> lore;
	
	private int hideFlags;
	private Map<Enchantment, Integer> enchantments;
	
	public ItemTag(ItemStack item) {
		this.item = ItemUtils.toCraftItem(item);
		this.tag = ItemUtils.getNBT(item);
		this.init();
	}
	
	protected void init() {
		NBTTagCompound display = this.tag.get("display", NBTTagCompound.class);
		
		this.hideFlags = this.tag.get("HideFlags", NBTTagInt.class, new NBTTagInt(0)).get();
		this.initEnchantments(this.tag.get("ench", NBTTagList.class));
		
		if (display != null) this.initDisplay(display);
	}
	
	protected void initDisplay(NBTTagCompound display) {
		NBTTag tag = display.get("Name", NBTTagString.class);
		if (tag != null) this.title = ((NBTTagString) tag).get();
		
		tag = display.get("Lore", NBTTagList.class);
		if (tag != null) {
			this.lore = new ArrayList<String>();
			for (NBTTag data : ((NBTTagList) tag).toArray()) this.lore.add(((NBTTagString) data).get());
		}
	}
	
	protected void initEnchantments(NBTTagList tag) {
		this.enchantments = new HashMap<Enchantment, Integer>();
		if (tag == null) return;
		
		for (int i = 0; i < tag.size(); i++) {
			NBTTagCompound ench = (NBTTagCompound) tag.get(i);
			this.enchantments.put(Enchantment.getById(0xffff & ench.get("id", NBTTagShort.class).get()), 0xffff & ench.get("lvl", NBTTagShort.class).get());
		}
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public List<String> getLore() {
		List<String> lore = new ArrayList<String>();
		for (String line : this.lore) lore.add(line);
		return lore;
	}
	
	public void setLore(String... lore) {
		if (lore == null) {
			this.lore = null;
			return;
		}
		
		this.lore = new ArrayList<String>();
		for (String line : lore) this.lore.add(line);
	}
	
	public void setLore(List<String> lore) {
		if (lore == null) {
			this.lore = null;
			return;
		}
		
		this.lore = new ArrayList<String>();
		for (String line : lore) this.lore.add(line);
	}
	
	public void addHideFlag(HideFlag flag) {
		if (flag.hasFlag(this.hideFlags)) return;
		this.hideFlags += flag.getCode();
	}
	
	public void addHideFlag() {
		this.hideFlags = 0;
		for (HideFlag flag : HideFlag.values()) this.hideFlags += flag.getCode();
	}
	
	public void removeHideFlag(HideFlag flag) {
		if (!flag.hasFlag(this.hideFlags)) return;
		this.hideFlags -= flag.getCode();
	}
	
	public void removeHideFlag() {
		this.hideFlags = 0;
	}
	
	public boolean hasHideFlag(HideFlag flag) {
		return flag.hasFlag(this.hideFlags);
	}
	
	public int getEnchantmentLevel(Enchantment enchantment) {
		Integer level = this.enchantments.get(enchantment);
		if (level == null) return 0;
		else return level.intValue();
	}
	
	public boolean hasEnchantment(Enchantment enchantment) {
		return this.getEnchantmentLevel(enchantment) > 0;
	}
	
	public void setEnchantmentLevel(Enchantment enchantment, int level) {
		if (level < 1) this.enchantments.remove(enchantment);
		else this.enchantments.put(enchantment, level);
	}
	
	public void addEnchantmentLevel(Enchantment enchantment, int level) {
		this.setEnchantmentLevel(enchantment, this.getEnchantmentLevel(enchantment) + level);
	}
	
	public void clearEnchantments() {
		this.enchantments.clear();
	}
	
	public ItemStack apply() {
		NBTTagCompound display = this.tag.get("display", NBTTagCompound.class);
		if (display == null) {
			display = new NBTTagCompound();
			this.tag.set("display", display);
		}
		
		if (this.hideFlags == 0) this.tag.remove("HideFlags");
		else this.tag.set("HideFlags", new NBTTagInt(this.hideFlags));
		
		NBTTagList list = this.applyEnchantments();
		if (list == null) this.tag.remove("ench");
		else this.tag.set("ench", list);
		
		this.applyDisplay(display);
		
		ItemUtils.setNBT(this.item, this.tag);
		return this.item;
	}
	
	protected void applyDisplay(NBTTagCompound display) {
		if (this.title == null) display.remove("Name");
		else display.set("Name", new NBTTagString(this.title));
		
		if (this.lore == null) display.remove("Lore");
		else {
			NBTTagList list = new NBTTagList();
			for (String line : this.lore) list.add(new NBTTagString(line));
			
			display.set("Lore", list);
		}
	}
	
	protected NBTTagList applyEnchantments() {
		if (this.enchantments.size() < 1) return null;
		NBTTagList list = new NBTTagList();
		for (Enchantment enchantment : this.enchantments.keySet()) {
			NBTTagCompound ench = new NBTTagCompound();
			ench.set("id", new NBTTagShort((short) enchantment.getId()));
			ench.set("lvl", new NBTTagShort(this.enchantments.get(enchantment).shortValue()));
			list.add(ench);
		}
		return list;
	}
	
}
