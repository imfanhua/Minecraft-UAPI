package me.fanhua.uapi.gui_old.ui.location;

public class UILocationCentered extends UILocation {
	
	private int width;
	private int height;
	
	public UILocationCentered(int x, int y, int width, int height) {
		super(x, y);
		
		this.width = width;
		this.height = height;
	}
	
	public void setSize(int width, int height) {
		this.width = width;
		this.height = height;
	}
	
	public UILocationCentered setWidth(int width) {
		this.width = width;
		return this;
	}
	
	public UILocationCentered setHeight(int height) {
		this.height = height;
		return this;
	}
	
	public UILocationCentered addWidth(int width) {
		this.width += width;
		return this;
	}
	
	public UILocationCentered addHeight(int height) {
		this.height += height;
		return this;
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public int getHiehgt() {
		return this.height;
	}
	
	public UILocationCentered clone() {
		return new UILocationCentered(this.x, this.y, this.width, this.height);
	}
	
	@Override
	public UILocation copy() {
		return this.clone();
	}
	
}
