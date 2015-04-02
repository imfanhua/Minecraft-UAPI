package me.fanhua.uapi.gui;

import me.fanhua.uapi.gui.render.Render;
import me.fanhua.uapi.gui.ui.UI;

public interface GuiContainer {
	
	public void draw(Render render, int tick);
	public void update(int tick);
	
	public void onMouseClicked(int key, boolean right, boolean shift, int x, int y);
	
	public boolean hasUI(UI ui);
	public UI addUI(UI ui);
	public boolean removeUI(UI ui);
	public UI removeUI(int index);
	public UI getUI(int index);
	public int getUI(UI ui);
	public int getUISize();
	public int clearUI();
	
}
