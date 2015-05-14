package me.fanhua.uapi.gui.ui;

import me.fanhua.uapi.event.base.bus.UBus;
import me.fanhua.uapi.event.base.event.UEvent;
import me.fanhua.uapi.gui.event.ClickAction;
import me.fanhua.uapi.gui.render.Render;

import org.bukkit.inventory.ItemStack;

public class UICheckBox extends UI {
	
	public final UBus Bus = new UBus();
	
	public class SelectedEvent implements UEvent {
		
		public UICheckBox getUI() {
			return UICheckBox.this;
		}
		
		public boolean isSelected() {
			return UICheckBox.this.isSelected();
		}
		
	}
	
	private int x;
	private int y;
	
	private boolean select;
	
	private ItemStack icon;
	private ItemStack selected;
	
	public UICheckBox(int x, int y, ItemStack icon, ItemStack selected) {
		this(x, y, false, icon, selected);
	}
	
	public UICheckBox(int x, int y, boolean select, ItemStack icon, ItemStack selected) {
		this.x = x;
		this.y = y;
		
		this.icon = icon;
		this.selected = selected;
		
		this.select = select;
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
	
	public boolean isSelected() {
		return this.select;
	}
	
	public void setSelected(boolean selected) {
		if (this.select == selected) return;
		
		this.select = selected;
		this.setNeedDraw();
	}
	
	public void setBoxIcon(ItemStack box) {
		this.icon = this.newItem(box);
		this.setNeedDraw();
	}
	
	public ItemStack getBoxIcon() {
		return this.newItem(this.icon);
	}
	
	public void setSelectedIcon(ItemStack box) {
		this.selected = this.newItem(box);
		this.setNeedDraw();
	}
	
	public ItemStack getSelectedIcon() {
		return this.newItem(this.selected);
	}
	
	@Override
	public void draw(Render render, int tick) {
		if (!this.isVisibled()) return;
		
		render.draw(this.x, this.y, this.select ? this.selected : this.icon);
	}
	
	@Override
	public void onGuiClicked(int x, int y, ClickAction action) {
		if (!this.isCanDoAction()) return;
		
		if (x != this.x || y != this.y || action.getType() != ClickAction.MOUSE) return;
		this.setSelected(!this.select);
		UBus.report(this.Bus.call(new SelectedEvent()));
	}
	
}
