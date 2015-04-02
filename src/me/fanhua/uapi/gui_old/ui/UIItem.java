package me.fanhua.uapi.gui_old.ui;

import org.bukkit.inventory.ItemStack;

import com.google.common.io.ByteArrayDataOutput;

import me.fanhua.uapi.gui_old.Gui;
import me.fanhua.uapi.gui_old.ui.location.UILocation;
import me.fanhua.uapi.network.Network;
import me.fanhua.uapi.network.packet.NetPacketUIMoveLocation;
import me.fanhua.uapi.network.packet.NetPacketUISetItem;
import me.fanhua.uapi.network.packet.NetPacketUISetVisible;

public class UIItem extends UI {
	
	private UILocation location;
	private int id;
	private int size;
	private int damage;
	private boolean enchantment;
	
	private boolean visible;
	
	public UIItem(Gui screen, UILocation location, ItemStack item, boolean enchantment) {
		this(screen, location, item.getTypeId(), item.getAmount(), item.getDurability(), enchantment);
	}
	
	public UIItem(Gui screen, UILocation location, int id, int size, int damage, boolean enchantment) {
		super(screen);
		
		this.location = location;
		this.id = id;
		this.size = size;
		this.damage = damage;
		this.enchantment = enchantment;
		
		this.visible = true;
	}
	
	public UILocation getLocation() {
		return this.location;
	}
	
	public void setLocation(UILocation location) {
		this.location = location;
		
		try {
			Network.send(this.getScreen().getUser(), new NetPacketUIMoveLocation(this, this.location));
		} catch (Throwable error) {}
	}
	
	public int getItemId() {
		return this.id;
	}
	
	public int getItemSize() {
		return this.size;
	}
	
	public int getItemDamage() {
		return this.damage;
	}
	
	public boolean isItemEnchantment() {
		return this.enchantment;
	}
	
	public void setItem(ItemStack item, boolean enchantment) {
		this.setItem(item.getTypeId(), item.getAmount(), item.getDurability(), enchantment);
	}
	
	public void setItem(int id, int size, int damage, boolean enchantment) {
		this.id = id;
		this.size = size;
		this.damage = damage;
		this.enchantment = enchantment;
		
		try {
			Network.send(this.getScreen().getUser(), new NetPacketUISetItem(this, this.id, this.size, this.damage, this.enchantment));
		} catch (Throwable error) {}
	}
	
	public boolean isVisible() {
		return this.visible;
	}
	
	public void setVisible(boolean visible) {
		this.visible = visible;
		
		try {
			Network.send(this.getScreen().getUser(), new NetPacketUISetVisible(this, this.visible));
		} catch (Throwable error) {}
	}
	
	@Override
	public void wirte(ByteArrayDataOutput output) {
		NetworkUI.wirteLocation(output, this.location);
		
		output.writeInt(this.id);
		output.writeInt(this.size);
		output.writeInt(this.damage);
		output.writeBoolean(this.enchantment);
	}
	
}
