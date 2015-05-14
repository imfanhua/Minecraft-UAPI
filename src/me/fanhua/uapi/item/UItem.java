package me.fanhua.uapi.item;

import me.fanhua.uapi.event.base.bus.UBus;
import me.fanhua.uapi.item.event.UItemEvent;
import me.fanhua.uapi.nbt.NBTTagCompound;
import me.fanhua.uapi.nbt.NBTTagString;
import me.fanhua.uapi.utils.ItemUtils;

import org.bukkit.inventory.ItemStack;

public abstract class UItem {
	
	private UBus bus;
	
	private String id;
	private ItemStack item;
	
	public UItem(String id, ItemStack item) {
		this.bus = new UBus();
		this.bus.register(this);
		
		item = item.clone();
		item.setAmount(1);
		
		NBTTagCompound tag = ItemUtils.getNBT(item);
		tag.set("uitem", new NBTTagString(this.id));
		this.item = ItemUtils.setCraftNBT(item, tag);
	}
	
	public String getId() {
		return this.id;
	}
	
	public ItemStack getItem() {
		return this.item.clone();
	}
	
	public ItemStack getItem(int amount) {
		ItemStack item = this.item.clone();
		item.setAmount(amount);
		return item;
	}
	
	public final UItemEvent call(UItemEvent event, UItemEvent object) {
		event.setCancelle(object.isCancelled());
		return this.call(event);
	}
	
	public final UItemEvent call(UItemEvent event) {
		UBus.report(this.bus.call(event));
		return event;
	}
	
}
