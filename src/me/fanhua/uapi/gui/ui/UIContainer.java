package me.fanhua.uapi.gui.ui;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;

import me.fanhua.uapi.gui.GuiContainer;
import me.fanhua.uapi.gui.event.ClickAction;
import me.fanhua.uapi.gui.render.Render;
import me.fanhua.uapi.gui.ui.UI;

public abstract class UIContainer extends UI implements GuiContainer {
	
	private List<UI> ui;
	
	private boolean needDraw;
	
	public UIContainer() {
		this.ui = new ArrayList<UI>();
	}
	
	public Player getPlayer() {
		return this.getContainer().getPlayer();
	}
	
	@Override
	public void draw(Render render, int tick) {
		for (UI ui : this.ui) ui.draw(render, tick);
	}
	
	@Override
	public void update(int tick) {
		for (UI ui : this.ui) {
			ui.update(tick);
			if (ui.isNeedDraw()) this.needDraw = true;
		}
	}
	
	@Override
	public boolean hasAnimation() {
		for (UI ui : this.ui) if (ui.hasAnimation()) return true;
		return false;
	}
	
	@Override
	public boolean isNeedDraw() {
		if (!this.needDraw) return false;
		this.needDraw = false;
		return true;
	}
	
	@Override
	public void setNeedDraw() {
		if (this.getContainer() == null) this.needDraw = true;
		else this.getContainer().setNeedDraw();
	}
	
	@Override
	public void onGuiClicked(int x, int y, ClickAction action) {
		for (UI ui : this.ui) ui.onGuiClicked(x, y, action);
	}
	
	@Override
	public void onInventoryClicked(int index, ClickAction action) {
		for (UI ui : this.ui) ui.onInventoryClicked(index, action);
	}
	
	@Override
	public boolean hasUI(UI ui) {
		return this.ui.contains(ui);
	}
	
	@Override
	public UI addUI(UI ui) {
		if (this.hasUI(ui)) return ui;
		
		this.ui.add(ui);
		ui.setUIContainer(this);
		this.needDraw = true;
		return ui;
	}
	
	@Override
	public boolean removeUI(UI ui) {
		if (!this.ui.remove(ui)) return false;
		ui.removeUIContainer(this);
		this.needDraw = true;
		return true;
	}
	
	@Override
	public UI removeUI(int index) {
		UI ui = this.ui.remove(index);
		ui.removeUIContainer(this);
		this.needDraw = true;
		return ui;
	}
	
	@Override
	public UI getUI(int index) {
		return this.ui.get(index);
	}

	@Override
	public int getUI(UI ui) {
		return this.ui.indexOf(ui);
	}

	@Override
	public int getUISize() {
		return this.ui.size();
	}

	@Override
	public int clearUI() {
		int size = this.ui.size();
		while (this.ui.size() > 0) this.removeUI(0);
		this.needDraw = true;
		return size;
	}
	
	private int offsetX;
	private int offsetY;
	private int resetWidth;
	private int resetHeight;
	
	protected void transform(Render render, int x, int y, int width, int height) {
		this.offsetX = x;
		this.offsetY = y;
		this.resetWidth = render.getWidth();
		this.resetHeight = render.getHeight();
		
		render.transform(x, y);
		render.resize(this.resetWidth < width ? this.resetWidth : width, this.resetHeight < height ? this.resetHeight : height);
	}
	
	protected void reset(Render render) {
		render.transform(-this.offsetX, -this.offsetY);
		render.resize(this.resetWidth, this.resetHeight);
 	}
	
}
