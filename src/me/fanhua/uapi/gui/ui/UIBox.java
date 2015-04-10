package me.fanhua.uapi.gui.ui;

import me.fanhua.uapi.gui.event.ClickAction;
import me.fanhua.uapi.gui.render.Render;

import org.bukkit.inventory.ItemStack;

public class UIBox extends UIContainer {
	
	private int x;
	private int y;
	private int width;
	private int height;
	
	private ItemStack box;
	private ItemStack background;
	
	public UIBox(int x, int y, int width, int height) {
		this(x, y, width, height, null);
	}
	
	public UIBox(int x, int y, int width, int height, ItemStack box) {
		this(x, y, width, height, box, null);
	}
	
	public UIBox(int x, int y, int width, int height, ItemStack box, ItemStack background) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		this.box = this.newItem(box);
		this.background = this.newItem(background);
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
	
	public void setPoint(int x, int y) {
		this.x = x;
		this.y = y;
		this.setNeedDraw();
	}

	public int getWidth() {
		return this.width;
	}

	public void setWidth(int width) {
		this.width = width;
		this.setNeedDraw();
	}

	public int getHeight() {
		return this.height;
	}

	public void setHeight(int height) {
		this.height = height;
		this.setNeedDraw();
	}
	
	public void setSize(int width, int height) {
		this.width = width;
		this.height = height;
		this.setNeedDraw();
	}
	
	public void setBoxIcon(ItemStack box) {
		this.box = this.newItem(box);
		this.setNeedDraw();
	}
	
	public void setBackground(ItemStack background) {
		this.background = this.newItem(background);
		this.setNeedDraw();
	}
	
	public ItemStack getBoxIcon() {
		return this.newItem(this.box);
	}

	public ItemStack getBackground() {
		return this.newItem(this.background);
	}
	
	public boolean hasBorder() {
		return this.box != null;
	}
	
	@Override
	public void draw(Render render, int tick) {
		if (!this.isVisibled()) return;
		
		int x = this.x;
		int y = this.y;
		int width = this.width;
		int height = this.height;
		
		if (!this.hasBorder()) {
			if (this.background != null) render.fillBox(this.x, this.y, this.width, this.height, this.background);
		} else {
			x += 1;
			y += 1;
			width -= 2;
			height -= 2;
			
			if (this.box != null) render.drawBox(this.x, this.y, this.width, this.height, this.box);
			if (this.background != null) render.fillBox(this.x + 1, this.y + 1, this.width - 2, this.height - 2, this.background);
		}
		
		this.transform(render, x, y, width, height);
		super.draw(render, tick);
		this.reset(render);
	}
	
	@Override
	public void onGuiClicked(int x, int y, ClickAction action) {
		if (!this.isCanDoAction()) return;
		
		x -= this.x;
		y -= this.y;
		int width = this.width;
		int height = this.height;
		
		if (this.hasBorder()) {
			x += 1;
			y += 1;
			width -= 2;
			height -= 2;
		}
		
		if (x < 0 || y < 0 || x >= width || y >= height) return;
		
		super.onGuiClicked(x, y, action);
	}
	
	@Override
	public void onInventoryClicked(int index, ClickAction action) {
		if (!this.isCanDoAction()) return;
		super.onInventoryClicked(index, action);
	}
	
}
