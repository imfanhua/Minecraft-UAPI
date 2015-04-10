package me.fanhua.uapi.gui.event.ui;

import me.fanhua.uapi.gui.event.ClickAction;
import me.fanhua.uapi.gui.ui.UIButton;

public class UIButtonClickEvent {
	
	private UIButton ui;
	private ClickAction action;
	
	public UIButtonClickEvent(UIButton ui, ClickAction action) {
		this.ui = ui;
		this.action = action;
	}
	
	public UIButton getUI() {
		return this.ui;
	}
	
	public ClickAction getAction() {
		return this.action;
	}
	
}
