package me.fanhua.uapi.item.builder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.fanhua.uapi.item.tag.HideFlag;
import me.fanhua.uapi.item.tag.ItemTag;
import me.fanhua.uapi.utils.ItemUtils;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

public class ItemBuilder {
	
	private int id;
	private int amount;
	private int data;
	
	private String title;
	private String[] lore;
	
	private List<HideFlag> hideFlags;
	private Map<Enchantment, Integer> enchantments;
	
	public ItemBuilder(int id) {
		this(id, 0, 1);
	}
	
	public ItemBuilder(int id, int data) {
		this(id, data, 1);
	}
	
	public ItemBuilder(int id, int data, int amount) {
		this.id = id;
		this.data = data;
		this.amount = amount;
		
		this.hideFlags = new ArrayList<HideFlag>();
		this.enchantments = new HashMap<Enchantment, Integer>();
	}
	
	public int type() {
		return this.id;
	}
	
	public ItemBuilder type(int id) {
		this.id = id;
		return this;
	}
	
	public int amount() {
		return this.amount;
	}
	
	public ItemBuilder amount(int amount) {
		this.amount = amount;
		return this;
	}
	
	public int data() {
		return this.data;
	}
	
	public ItemBuilder data(int data) {
		this.data = data;
		return this;
	}
	
	public String title() {
		return this.title;
	}
	
	public ItemBuilder title(String title) {
		this.title = title;
		return this;
	}
	
	public String[] lore() {
		return this.lore;
	}
	
	public ItemBuilder lore(String... lore) {
		this.lore = new String[lore.length];
		for (int i = 0; i < lore.length; i++) this.lore[i] = lore[i];
		return this;
	}
	
	public HideFlag[] hide() {
		return this.hideFlags.toArray(new HideFlag[this.hideFlags.size()]);
	}
	
	public ItemBuilder hide(HideFlag... flags) {
		for (HideFlag flag : flags) if (!this.hideFlags.contains(flag)) this.hideFlags.add(flag);
		return this;
	}
	
	public ItemBuilder hideAll() {
		this.hideFlags.clear();
		for (HideFlag flag : HideFlag.values()) this.hideFlags.add(flag);
		return this;
	}
	
	public ItemBuilder remodeHide() {
		this.hideFlags.clear();
		return this;
	}
	
	public ItemBuilder remodeHide(HideFlag... flags) {
		for (HideFlag flag : flags) this.hideFlags.remove(flag);
		return this;
	}
	
	public Map<Enchantment, Integer> enchant() {
		return this.enchantments;
	}
	
	public int enchant(Enchantment enchantment) {
		Integer level = this.enchantments.get(enchantment);
		if (level == null) return 0;
		else return level.intValue();
	}
	
	public ItemBuilder enchant(Enchantment enchantment, int level) {
		this.enchantments.put(enchantment, level);
		return this;
	}
	
	public ItemBuilder enchant(Enchantment enchantment, int level, boolean add) {
		if (add) this.enchant(enchantment, this.enchant(enchantment) + level);
		else this.enchant(enchantment, level);
		return this;
	}
	
	public ItemStack build() {
		ItemStack item = new ItemStack(this.id, this.amount, (short) this.data);
		return this.build(ItemUtils.toCraftItem(item), this.title, this.lore);
	}
	
	protected ItemStack build(ItemStack item, String title, String[] lore) {
		this.build(new ItemTag(item), title, lore);
		return item;
	}
	
	protected void build(ItemTag tag, String title, String[] lore) {
		tag.setTitle(this.title);
		tag.setLore(this.lore);
		
		for (HideFlag flag : this.hideFlags) tag.addHideFlag(flag);
		for (Enchantment enchantment : this.enchantments.keySet()) tag.setEnchantmentLevel(enchantment, this.enchantments.get(enchantment));
		
		tag.apply();
	}
	
}
