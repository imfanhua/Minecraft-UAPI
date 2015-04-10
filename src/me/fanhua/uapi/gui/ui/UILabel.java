package me.fanhua.uapi.gui.ui;

import me.fanhua.uapi.gui.render.Render;

import org.bukkit.inventory.ItemStack;

public class UILabel extends UI {
	
	private int x;
	private int y;
	
	private ItemStack icon;
	
	public UILabel(int x, int y, ItemStack icon) {
		this.x = x;
		this.y = y;
		
		this.icon = icon;
	}
	
	public int getX() {
		return this.x;
	}

	public void setX(int x) {
		this.x = x;
		this.setNeedDraw();
	}

	public int getY() {
		return this.y;
	}

	public void setY(int y) {
		this.y = y;
		this.setNeedDraw();
	}
	
	public void setLabelIcon(ItemStack box) {
		this.icon = this.newItem(box);
		this.setNeedDraw();
	}
	
	public ItemStack getLabelIcon() {
		return this.newItem(this.icon);
	}
	
	@Override
	public void draw(Render render, int tick) {
		if (!this.isVisibled()) return;
		
		render.draw(this.x, this.y, this.icon);
	}
	
}
