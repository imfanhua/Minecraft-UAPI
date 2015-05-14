package me.fanhua.uapi.gui.event.tool;

import me.fanhua.uapi.event.base.event.UEvent;
import me.fanhua.uapi.gui.tool.CheckBoxLinker;
import me.fanhua.uapi.gui.ui.UICheckBox;

public class CheckBoxSelectedEvent implements UEvent {
	
	private CheckBoxLinker linker;
	
	private int code;
	private UICheckBox ui;
	
	public CheckBoxSelectedEvent(CheckBoxLinker linker, int code, UICheckBox ui) {
		this.linker = linker;
		
		this.code = code;
		this.ui = ui;
	}
	
	public CheckBoxLinker getLinker() {
		return this.linker;
	}
	
	public UICheckBox getUI() {
		return this.ui;
	}
	
	public int getCode() {
		return this.code;
	}
	
}
