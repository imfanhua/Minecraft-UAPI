package me.fanhua.uapi.user.api;

import me.fanhua.uapi.gui.Gui;
import me.fanhua.uapi.user.User;

public class UserGuiManager {
	
	public static UserGuiManager create(User user) {
		return new UserGuiManager(user);
	}
	
	private User user;
	private Gui gui;
	
	private UserGuiManager(User user) {
		this.user = user;
	}
	
	public User getUser() {
		return this.user;
	}
	
	public boolean hasGui() {
		return this.gui != null;
	}
	
	public void open(Gui gui) {
		if (this.hasGui()) this.close();
		
	}
	
	public Gui getGui() {
		return this.gui;
	}
	
	public <T extends Gui> T getGui(Class<? extends T> type) {
		try {
			return (T) this.getGui();
		} catch (Throwable error) {
			return null;
		}
	}
	
	public void close() {
		if (this.gui != null) return;
	}
	
}
