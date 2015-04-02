package me.fanhua.uapi.event.self.ui;

import me.fanhua.uapi.gui_old.ui.UIButton;

public class UIButtonClickEvent {
	
	private UIButton ui;
	
	public UIButtonClickEvent(UIButton ui) {
		this.ui = ui;
	}
	
	public UIButton getUI() {
		return this.ui;
	}
	
}
