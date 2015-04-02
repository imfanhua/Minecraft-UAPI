package me.fanhua.uapi.event.self.ui;

import me.fanhua.uapi.gui_old.ui.UIPasswordField;

public class UIPasswordFieldTypedEvent {
	
	private UIPasswordField ui;
	private String old;
	
	public UIPasswordFieldTypedEvent(UIPasswordField ui, String old) {
		this.ui = ui;
		this.old = old;
	}
	
	public UIPasswordField getUI() {
		return this.ui;
	}
	
	public String getOldText() {
		return this.old;
	}
	
}
