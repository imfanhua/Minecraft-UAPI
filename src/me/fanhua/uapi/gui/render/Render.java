package me.fanhua.uapi.gui.render;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public abstract class Render {
	
	private Inventory inventory;
	
	private ItemStack[] data;
	
	private int offsetX;
	private int offsetY;
	
	private int resizeWidth;
	private int resizeHeight;
	
	public Render(Inventory inventory) {
		this.inventory = inventory;
	}
	
	protected void init() {
		this.data = new ItemStack[this.getMaxWidth() * this.getMaxHeight()];
		
		this.resizeWidth = this.getMaxWidth();
		this.resizeHeight = this.getMaxHeight();
	}
	
	protected abstract int getMaxWidth();
	protected abstract int getMaxHeight();
	
	public int getWidth() {
		return this.resizeWidth;
	}
	
	public int getHeight() {
		return this.resizeHeight;
	}
	
	protected Inventory getInventory() {
		return inventory;
	}
	
	protected ItemStack[] getData() {
		return this.data;
	}
	
	protected void clearData() {
		this.data = null;
	}
	
	private int getPoint(int x, int y) {
		return x + this.getMaxWidth() * y;
	}
	
	private boolean canDraw(int x, int y) {
		return x >= 0 && x < this.resizeWidth + this.offsetX && x < this.getMaxWidth() && y >= 0 && y < this.resizeHeight + this.offsetY && y < this.getMaxHeight();
	}
	
	private ItemStack newItem(ItemStack item) {
		return item == null ? null : item.clone();
	}
	
	public void transform(int x, int y) {
		this.offsetX += x;
		this.offsetY += y;
	}
	
	public void resize(int width, int height) {
		this.resizeWidth = width < 0 ? 0 : width;
		this.resizeHeight = height < 0 ? 0 : height;
	}
	
	public void reset() {
		this.offsetX = 0;
		this.offsetY = 0;
		
		this.resizeWidth = this.getMaxWidth();
		this.resizeHeight = this.getMaxHeight();
	}
	
	public void clear(int x, int y) {
		this.draw(x, y, null);
	}
	
	public void draw(int x, int y, ItemStack item) {
		x += this.offsetX;
		y += this.offsetY;
		if (this.canDraw(x, y)) this.data[this.getPoint(x, y)] = this.newItem(item);
	}
	
	public void drawLine(int x, int y, int length, boolean horizontal, ItemStack item) {
		if (horizontal) for (int offset = 0; offset < length; offset++) this.draw(x + offset, y, item);
		else for (int offset = 0; offset < length; offset++) this.draw(x, y + offset, item);
	}
	
	public void drawBox(int x, int y, int width, int height, ItemStack item) {
		this.drawLine(x, y, width, true, item);
		this.drawLine(x, y + height - 1, width, true, item);
		
		this.drawLine(x, y + 1, height - 2, false, item);
		this.drawLine(x + width - 1, y + 1, height - 2, false, item);
	}
	
	public void fillBox(int x, int y, int width, int height, ItemStack item) {
		for (int offsetX = 0; offsetX < width; offsetX++) for (int offsetY = 0; offsetY < height; offsetY++) this.draw(x + offsetX, y + offsetY, item);
	}
	
}
