package me.fanhua.uapi.item.builder;

import me.fanhua.uapi.item.tag.ItemTagLeather;

import org.bukkit.Color;
import org.bukkit.inventory.ItemStack;

public class ItemBuilderLeather extends ItemBuilder {

	public ItemBuilderLeather(int id) {
		super(id);
	}
	
	public ItemBuilderLeather(int id, int data) {
		super(id, data);
	}
	
	public ItemBuilderLeather(int id, int data, int amount) {
		super(id, data, amount);
	}

	private Color color;
	
	public Color color() {
		if (this.color == null) return null;
		else return Color.fromRGB(this.color.asRGB());
	}
	
	public ItemBuilderLeather color(Color color) {
		if (color == null) this.color = null;
		else this.color = Color.fromRGB(color.asRGB());
		
		return this;
	}
	
	protected ItemStack build(ItemStack item, String title, String[] lore) {
		ItemTagLeather tag = new ItemTagLeather(item);
		tag.setColor(this.color);
		
		this.build(tag, title, lore);
		return item;
	}

}
