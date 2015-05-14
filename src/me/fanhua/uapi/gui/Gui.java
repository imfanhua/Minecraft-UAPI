package me.fanhua.uapi.gui;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;

import me.fanhua.uapi.gui.event.ClickAction;
import me.fanhua.uapi.gui.render.Render;
import me.fanhua.uapi.gui.render.RenderObject;
import me.fanhua.uapi.gui.type.GuiType;
import me.fanhua.uapi.gui.ui.UI;
import me.fanhua.uapi.user.User;
import me.fanhua.uapi.user.manager.UserGuiManager;

public abstract class Gui implements GuiContainer {
	
	private GuiType type;
	
	private List<UI> ui;
	
	private boolean needDraw;
	
	private UserGuiManager manager;
	
	private int tick;
	private int tickTime;
	
	public Gui(GuiType type) {
		this.type = type;
		
		this.ui = new ArrayList<UI>();
	}
	
	public GuiType getType() {
		return this.type;
	}
	
	public UserGuiManager getManager() {
		return this.manager;
	}
	
	public User getUser() {
		return this.manager.getUser();
	}
	
	public Player getPlayer() {
		return this.getUser().getPlayer();
	}
	
	public void onOpen() {}
	
	public void onClose() {
		this.manager = null;
	}
	
	public void tick() {
		int tick = this.tick++;
		if (this.tick < 0) this.tick = 0;
		
		this.update(tick);
		
		if (this.needDraw) {
			this.needDraw = false;
			this.draw();
		}
	}
	
	public int getTick() {
		return this.tick;
	}
	
	public void init(UserGuiManager manager) {
		this.manager = manager;
		this.type.initInventory(this.getPlayer());
		
		this.tick = 0;
		this.tick();
		
		this.getPlayer().openInventory(this.type.getInventory());
	}
	
	public void onTickTime() {
		if (this.tickTime++ >= this.getTickTime()) {
			this.tickTime = 0;
			this.tick();
		} else if (this.needDraw) {
			this.needDraw = false;
			this.draw();
		}
	}
	
	public int getTickTime() {
		return 10;
	}
	
	private void draw() {
		RenderObject render = this.type.newRender();
		this.draw(render, tick);
		render.draw();
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
		return this.needDraw;
	}
	
	@Override
	public void setNeedDraw() {
		this.needDraw = true;
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
	
	public void close() {
		if (this.manager == null) return;
		this.manager.close();
	}
	
}
