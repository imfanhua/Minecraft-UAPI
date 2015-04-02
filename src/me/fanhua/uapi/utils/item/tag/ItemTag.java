package me.fanhua.uapi.utils.item.tag;

import java.util.ArrayList;
import java.util.List;

import me.fanhua.uapi.utils.item.ItemUtils;
import me.fanhua.uapi.utils.nbt.NBTTag;
import me.fanhua.uapi.utils.nbt.NBTTagCompound;
import me.fanhua.uapi.utils.nbt.NBTTagList;
import me.fanhua.uapi.utils.nbt.NBTTagString;

import org.bukkit.inventory.ItemStack;

public class ItemTag {
	
	private ItemStack item;
	protected NBTTagCompound tag;
	
	private String title;
	private List<String> lore;
	
	public ItemTag(ItemStack item) {
		this.item = ItemUtils.toCraftItem(item);
		this.tag = ItemUtils.getNBT(item);
		this.init();
	}
	
	protected void init() {
		NBTTagCompound display = this.tag.get("display", NBTTagCompound.class);
		if (display == null) return;
		
		this.initDisplay(display);
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
	
	public ItemStack apply() {
		NBTTagCompound display = this.tag.get("display", NBTTagCompound.class);
		if (display == null) {
			display = new NBTTagCompound();
			this.tag.set("display", display);
		}
		
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
	
}
