package me.fanhua.uapi.utils.item.builder;

import me.fanhua.uapi.utils.item.ItemUtils;
import me.fanhua.uapi.utils.item.tag.ItemTag;

import org.bukkit.inventory.ItemStack;

public class ItemBuilder {
	
	private int id;
	private int amount;
	private int data;
	
	private String title;
	private String[] lore;
	
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
		this.title = title.replaceAll("&", "\u00a7");
		return this;
	}
	
	public String[] lore() {
		return this.lore;
	}
	
	public ItemBuilder lore(String... lore) {
		this.lore = new String[lore.length];
		for (int i = 0; i < lore.length; i++) this.lore[i] = lore[i].replaceAll("&", "\u00a7");
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
		tag.apply();
	}
	
}
