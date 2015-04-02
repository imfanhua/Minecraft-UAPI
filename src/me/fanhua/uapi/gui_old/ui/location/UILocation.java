package me.fanhua.uapi.gui_old.ui.location;

public class UILocation {
	
	protected int x;
	protected int y;
	
	public UILocation(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void setLocation(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public UILocation setX(int x) {
		this.x = x;
		return this;
	}
	
	public UILocation setY(int y) {
		this.y = y;
		return this;
	}
	
	public UILocation addX(int x) {
		this.x += x;
		return this;
	}
	
	public UILocation addY(int y) {
		this.y += y;
		return this;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public UILocation copy() {
		return new UILocation(this.x, this.y);
	}
	
}
