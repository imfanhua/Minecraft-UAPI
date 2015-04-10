package me.fanhua.uapi.gui.event.ui;

import me.fanhua.uapi.gui.ui.UICheckBox;

public class UICheckBoxSelectedEvent {
	
	private UICheckBox ui;
	
	public UICheckBoxSelectedEvent(UICheckBox ui) {
		this.ui = ui;
	}
	
	public UICheckBox getUI() {
		return this.ui;
	}
	
	public boolean isSelected() {
		return this.ui.isSelected();
	}
	
}
