package me.fanhua.uapi.user.manager;

import org.bukkit.entity.Player;

import me.fanhua.uapi.gui.Gui;
import me.fanhua.uapi.task.TaskOpenGui;
import me.fanhua.uapi.user.User;
import me.fanhua.uapi.user.manager.base.IManagerRegister;
import me.fanhua.uapi.user.manager.base.IUserManager;

public final class UserGuiManager implements IUserManager {
	
	public static final IManagerRegister REGISTER = new Register();
	
	private static class Register implements IManagerRegister {

		@Override
		public void register(User user) {
			user.addManager(new UserGuiManager(user));
		}
		
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
		if (gui.getManager() != null) return;
		
		if (this.hasGui()) {
			this.close(false);
			TaskOpenGui.addTask(this.user, gui);
			return;
		}
		
		this.gui = gui;
		gui.init(this);
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
		this.close(false);
	}
	
	@Deprecated
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

	@Override
	public void onOffline() {
		this.close();
	}
	
}
