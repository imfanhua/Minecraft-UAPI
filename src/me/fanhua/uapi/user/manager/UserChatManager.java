package me.fanhua.uapi.user.manager;

import org.bukkit.entity.Player;

import me.fanhua.uapi.network.packet.IPacket;
import me.fanhua.uapi.network.packet.Packet;
import me.fanhua.uapi.user.User;
import me.fanhua.uapi.user.manager.base.IManagerRegister;
import me.fanhua.uapi.user.manager.base.IUserManager;
import me.fanhua.uapi.utils.ChatUtils;

public final class UserChatManager implements IUserManager {
	
	public static final IManagerRegister REGISTER = new Register();
	
	private static class Register implements IManagerRegister {

		@Override
		public void register(User user) {
			user.addManager(new UserChatManager(user));
		}
		
	}
	
	private User user;
	
	private UserChatManager(User user) {
		this.user = user;
	}
	
	public User getUser() {
		return this.user;
	}
	
	public void send(String... messages) {
		this.user.getPlayer().sendMessage(messages);
	}
	
	public void sendRaw(String... messages) {
		Player player = this.user.getPlayer();
		for (String message : messages) player.sendRawMessage(message);
	}
	
	public void sendRaw(Packet... messages) {
		for (Packet message : messages) this.user.send(message);
	}
	
	public void sendAction(String message) {
		this.sendActionRaw("{\"text\": \"" + message + "\"}");
	}
	
	public void sendActionRaw(String message) {
		this.sendActionRaw(ChatUtils.getActionPacket(ChatUtils.toChatObject(message)));
	}
	
	public void sendActionRaw(IPacket message) {
		this.user.send(message);
	}
	
	@Override
	public void onOffline() {}
	
}
