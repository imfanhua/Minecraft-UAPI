package me.fanhua.uapi.utils.item.tag;

import me.fanhua.uapi.utils.nbt.NBTTag;
import me.fanhua.uapi.utils.nbt.NBTTagCompound;
import me.fanhua.uapi.utils.nbt.NBTTagInt;

import org.bukkit.Color;
import org.bukkit.inventory.ItemStack;

public class ItemTagLeather extends ItemTag {
	
	private Color color;
	
	public ItemTagLeather(ItemStack item) {
		super(item);
	}
	
	protected void initDisplay(NBTTagCompound display) {
		super.initDisplay(display);
		
		NBTTag tag = display.get("color", NBTTagInt.class);
		if (tag != null) this.color = Color.fromRGB(((NBTTagInt) tag).get());
	}
	
	public Color getColor() {
		if (this.color == null) return null;
		else return Color.fromRGB(this.color.asRGB());
	}
	
	public void setColor(Color color) {
		if (color == null) {
			this.color = null;
			return;
		}
		
		this.color = Color.fromRGB(color.asRGB());
	}
	
	public boolean hasColor() {
		return this.color != null;
	}
	
	protected void applyDisplay(NBTTagCompound display) {
		super.applyDisplay(display);
		
		if (this.color == null) display.remove("color");
		else display.set("color", new NBTTagInt(this.color.asRGB()));
	}

}
