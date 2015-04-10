package me.fanhua.uapi.gui;

import org.bukkit.entity.Player;

import me.fanhua.uapi.gui.event.ClickAction;
import me.fanhua.uapi.gui.render.Render;
import me.fanhua.uapi.gui.ui.UI;

public interface GuiContainer {
	
	public void draw(Render render, int tick);
	public void update(int tick);
	
	public void onGuiClicked(int x, int y, ClickAction action);
	public void onInventoryClicked(int index, ClickAction action);
	
	public boolean hasUI(UI ui);
	public UI addUI(UI ui);
	public boolean removeUI(UI ui);
	public UI removeUI(int index);
	public UI getUI(int index);
	public int getUI(UI ui);
	public int getUISize();
	public int clearUI();
	
	public boolean hasAnimation();
	public boolean isNeedDraw();
	public void setNeedDraw();
	
	public Player getPlayer();
	
}
