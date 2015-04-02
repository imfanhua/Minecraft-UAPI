package me.fanhua.uapi.gui_old;

import java.util.HashMap;
import java.util.Map;

import me.fanhua.uapi.gui_old.ui.UI;
import me.fanhua.uapi.network.Network;
import me.fanhua.uapi.user.User;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public abstract class Gui {
	
	private User user;
	private String title;
	
	private boolean open;
	
	public Gui(User user, String title) {
		this.user = user;
		this.title = title;
		
		user.getGuiManager().addGui(this);
	}
	
	public User getUser() {
		return this.user;
	}
	
	public Player getPlayer() {
		return this.user.getPlayer();
	}
	
	public boolean isOpen() {
		return this.open;
	}
	
	public void open() {
		if (this.open) return;
		this.open = true;
		
	}
	
	public void close() {
		if (!this.open) return;
		this.open = false;
		
		this.onClosed();
	}
	
	public void onClose() {
		if (!this.open) return;
		this.open = false;
		
		this.onClosed();
	}
	
	public void onClosed() {}
	public void onRemoved() {}
	
	
	public void addUI(UI ui) {
		this.map.put(ui.getId(), ui);
	}
	
	public void removeUI(UI ui) {
		this.map.remove(ui.getId());
	}
	
	public UI getUI(int id) {
		return this.map.get(id);
	}
	
	public <T extends UI> T getUI(int id, Class<? extends T> type) {
		try {
			return (T) this.getUI(id);
		} catch (Throwable error) {
			return null;
		}
	}
	
}
