package me.fanhua.uapi.gui.ui;

import me.fanhua.uapi.event.base.UEvent;
import me.fanhua.uapi.gui.event.ClickAction;
import me.fanhua.uapi.gui.event.ui.UIButtonClickEvent;
import me.fanhua.uapi.gui.render.Render;

import org.bukkit.inventory.ItemStack;

public class UIButton extends UI {
	
	public final UEvent<UIButtonClickEvent> EventClicked = new UEvent<UIButtonClickEvent>();
	
	private int x;
	private int y;
	
	private ItemStack icon;
	
	public UIButton(int x, int y, ItemStack icon) {
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
	
	public void setButtonIcon(ItemStack box) {
		this.icon = this.newItem(box);
		this.setNeedDraw();
	}
	
	public ItemStack getButtonIcon() {
		return this.newItem(this.icon);
	}
	
	@Override
	public void draw(Render render, int tick) {
		if (!this.isVisibled()) return;
		
		render.draw(this.x, this.y, this.icon);
	}
	
	@Override
	public void onGuiClicked(int x, int y, ClickAction action) {
		if (!this.isCanDoAction()) return;
		
		if (x != this.x || y != this.y || action.getType() != ClickAction.MOUSE) return;
		this.EventClicked.call(new UIButtonClickEvent(this, action));
	}
	
}
