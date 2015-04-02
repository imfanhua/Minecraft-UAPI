package me.fanhua.uapi.gui;

import java.util.ArrayList;
import java.util.List;

import me.fanhua.uapi.gui.render.Render;
import me.fanhua.uapi.gui.type.GuiType;
import me.fanhua.uapi.gui.ui.UI;

public abstract class Gui implements GuiContainer {
	
	private GuiType type;
	
	private List<UI> ui;
	
	public Gui(GuiType type) {
		this.type = type;
		
		this.ui = new ArrayList<UI>();
	}
	
	public GuiType getType() {
		return this.type;
	}
	
	public void onOpen() {}
	public void onClose() {}
	
	private int tick;
	
	public void tick() {
		int tick = this.tick++;
		if (this.tick < 0) this.tick = 0;
		
		this.update(tick);
		this.draw(this.type.newRender(), tick);
	}
	
	public int getTick() {
		return this.tick;
	}
	
	@Override
	public void draw(Render render, int tick) {
		for (UI ui : this.ui) ui.draw(render, tick);
	}
	
	@Override
	public void update(int tick) {
		for (UI ui : this.ui) ui.update(tick);
	}
	
	@Override
	public void onMouseClicked(int key, boolean right, boolean shift, int x, int y) {
		for (UI ui : this.ui) ui.onMouseClicked(key, right, shift, x, y);
	}
	
	@Override
	public boolean hasUI(UI ui) {
		return this.ui.contains(ui);
	}
	
	@Override
	public UI addUI(UI ui) {
		if (this.hasUI(ui)) return ui;
		
		this.ui.add(ui);
		ui.addUI(this);
		return ui;
	}
	
	@Override
	public boolean removeUI(UI ui) {
		if (!this.ui.remove(ui)) return false;
		ui.removeUI(this);
		return true;
	}
	
	@Override
	public UI removeUI(int index) {
		UI ui = this.ui.remove(index);
		ui.removeUI(this);
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
		return size;
	}
	
}
