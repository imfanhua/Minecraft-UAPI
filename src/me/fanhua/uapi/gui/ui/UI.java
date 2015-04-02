package me.fanhua.uapi.gui.ui;

import me.fanhua.uapi.gui.GuiContainer;
import me.fanhua.uapi.gui.render.Render;

public abstract class UI {
	
	private GuiContainer container;
	
	public void draw(Render render, int tick) {}
	public void update(int tick) {}
	
	public void onMouseClicked(int key, boolean right, boolean shift, int x, int y) {}
	
	public void onUIAdded() {}
	public void onUIRemoved() {}
	
	public void addUI(GuiContainer container) {
		this.container = container;
		this.onUIAdded();
	}
	
	public void removeUI(GuiContainer container) {
		this.container = null;
		this.onUIRemoved();
	}
	
	public GuiContainer getContainer() {
		return this.container;
	}
	
}