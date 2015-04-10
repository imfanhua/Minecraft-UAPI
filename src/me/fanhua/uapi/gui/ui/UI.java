package me.fanhua.uapi.gui.ui;

import org.bukkit.inventory.ItemStack;

import me.fanhua.uapi.gui.GuiContainer;
import me.fanhua.uapi.gui.event.ClickAction;
import me.fanhua.uapi.gui.render.Render;

public abstract class UI {
	
	private boolean visible;
	private boolean enable;
	
	private GuiContainer container;
	
	public UI() {
		this.visible = true;
		this.enable = true;
	}
	
	public boolean isVisibled() {
		return this.visible;
	}
	
	public boolean isEnable() {
		return this.enable;
	}
	
	public void setVisibled(boolean visibled) {
		if (this.visible == visibled) return;
		
		this.visible = visibled;
		this.setNeedDraw();
	}
	
	public void setEnabled(boolean enabled) {
		if (this.enable == enabled) return;
		
		this.enable = enabled;
		this.setNeedDraw();
	}
	
	public boolean isCanDoAction() {
		return this.visible && this.enable;
	}
	
	public boolean hasAnimation() {
		return false;
	}
	
	public boolean isNeedDraw() {
		return this.hasAnimation();
	}
	
	public void draw(Render render, int tick) {}
	public void update(int tick) {}
	
	public void onGuiClicked(int x, int y, ClickAction action) {}
	public void onInventoryClicked(int index, ClickAction action) {}
	
	public void onUIAdded() {}
	public void onUIRemoved() {}
	
	public void setUIContainer(GuiContainer container) {
		this.container = container;
		this.onUIAdded();
	}
	
	public void removeUIContainer(GuiContainer container) {
		this.container = null;
		this.onUIRemoved();
	}
	
	public GuiContainer getContainer() {
		return this.container;
	}
	
	protected ItemStack newItem(ItemStack item) {
		return item == null ? null : item.clone();
	}
	
	protected void setNeedDraw() {
		if (this.container != null) this.container.setNeedDraw();
	}
	
}