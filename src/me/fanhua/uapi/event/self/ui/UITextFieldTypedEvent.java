package me.fanhua.uapi.event.self.ui;

import me.fanhua.uapi.gui_old.ui.UITextField;

public class UITextFieldTypedEvent {
	
	private UITextField ui;
	private String old;
	
	public UITextFieldTypedEvent(UITextField ui, String old) {
		this.ui = ui;
		this.old = old;
	}
	
	public UITextField getUI() {
		return this.ui;
	}
	
	public String getOldText() {
		return this.old;
	}
	
}
