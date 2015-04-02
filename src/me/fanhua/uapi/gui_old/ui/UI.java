package me.fanhua.uapi.gui_old.ui;

import com.google.common.io.ByteArrayDataOutput;

import me.fanhua.uapi.gui_old.Gui;

public abstract class UI {
	
	private int id;
	private Gui screen;
	
	public UI(Gui screen) {
		this.screen = screen;
		this.id = screen.getNewUIId();
	}
	
	public int getId() {
		return this.id;
	}
	
	public Gui getScreen() {
		return this.screen;
	}
	
	public abstract void wirte(ByteArrayDataOutput output);
	
	public void remove() {
		this.screen.removeUI(this);
	}
	
}
