package me.fanhua.uapi.user.manager;

import me.fanhua.uapi.user.User;
import me.fanhua.uapi.user.manager.base.IManagerRegister;
import me.fanhua.uapi.user.manager.base.IUserManager;

public final class UserUtilsManager implements IUserManager {
	
	public static final IManagerRegister REGISTER = new Register();
	
	private static class Register implements IManagerRegister {

		@Override
		public void register(User user) {
			user.addManager(new UserUtilsManager(user));
		}
		
	}
	
	private User user;
	
	private UserUtilsManager(User user) {
		this.user = user;
	}
	
	public User getUser() { 
		return this.user;
	}
	
	public void sitdown(String... messages) {
		this.user.getPlayer().sendMessage(messages);
	}

	@Override
	public void onOffline() {}
	
}
