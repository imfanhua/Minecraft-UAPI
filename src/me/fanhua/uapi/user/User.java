package me.fanhua.uapi.user;

import me.fanhua.uapi.network.Network;
import me.fanhua.uapi.network.Packet;
import me.fanhua.uapi.user.api.UserGuiManager;
import me.fanhua.uapi.user.api.UserSkillManager;
import me.fanhua.uapi.utils.particle.ParticleEffect;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class User {
	
	public static User toUser(Player player) {
		User user = UserManager.getUser(player);
		if (user != null) {
			user.player = player;
			return user;
		}
		
		user = new User(player);
		UserManager.setUser(user);
		return user;
	}
	
	private Player player;
	private UserGuiManager gui;
	private UserSkillManager skill;
	
	private User(Player player) {
		this.player = player;
	}
	
	public Player getPlayer() {
		return this.player;
	}
	
	public boolean isAdminGroup() {
		return this.getGroup() > 0;
	}
	
	public int getGroup() {
		return this.player.isOp() ? 999 : 0;
	}
	
	public boolean isVIP() {
		return this.getVIPLevel() > 0;
	}
	
	public int getVIPLevel() {
		return 0;
	}
	
	public int getCoin() {
		return 0;
	}
	
	public UserGuiManager getGuiManager() {
		return this.gui;
	}
	
	public UserSkillManager getSkillManager() {
		return this.skill;
	}
	
	public void resetAll() {
		if (this.gui != null) this.gui.close();
		this.gui = UserGuiManager.create(this);
		
		if (this.skill != null) this.skill.removeAll();
		this.skill = UserSkillManager.create(this);
	}
	
	public int getHoldSlot() {
		return this.player.getInventory().getHeldItemSlot();
	}
	
	public boolean setHoldSlot(int slot) {
		return this.send(new Packet("PlayOutHeldItemSlot").set("a", slot, false));
	}
	
	public void clearInventory() {
		PlayerInventory inventory = this.player.getInventory();
		inventory.clear();
		inventory.setArmorContents(new ItemStack[4]);
	}
	
	public boolean send(Packet packet) {
		try {
			Network.send(this.player, packet.getPacketObject());
			return true;
		} catch (Throwable error) {
			return false;
		}
	}
	
	public boolean play(ParticleEffect effect) {
		if (!effect.canSee(this.getPlayer().getLocation())) return true;
		
		try {
			return this.send(effect.getPacket());
		} catch (Throwable error) {
			return false;
		}
	}
	
}
