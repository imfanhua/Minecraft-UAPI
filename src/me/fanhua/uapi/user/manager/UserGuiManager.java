package me.fanhua.uapi.user.manager;

import org.bukkit.entity.Player;

import me.fanhua.uapi.gui.Gui;
import me.fanhua.uapi.task.TaskOpenGui;
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
		if (gui.getPlayer() != null) return;
		
		if (this.hasGui()) {
			this.close(false);
			TaskOpenGui.addTask(this.user, gui);
			return;
		}
		
		this.gui = gui;
		gui.init(this.user.getPlayer());
		this.user.getPlayer().openInventory(this.gui.getType().getInventory()); 
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
	
	
	public void close(boolean event) {
		if (this.gui == null) return;
		if (event) {
			this.gui.onClose();
			this.gui = null;
		} else {
			Player player = this.user.getPlayer();
			if (player.getOpenInventory() != null) player.closeInventory();
		}
	}
	
}
