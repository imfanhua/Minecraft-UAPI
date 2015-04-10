package me.fanhua.uapi.user;

import me.fanhua.uapi.gui.Gui;
import me.fanhua.uapi.network.Network;
import me.fanhua.uapi.network.Packet;
import me.fanhua.uapi.user.manager.UserGuiManager;
import me.fanhua.uapi.user.manager.UserSkillManager;
import me.fanhua.uapi.utils.particle.ParticleEffect;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class User {
	
	public static User toUser(Player player) {
		if (!player.isOnline()) return null;
		
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
	
	private boolean offline;
	
	private User(Player player) {
		this.player = player;
	}
	
	public Player getPlayer() {
		return this.player;
	}
	
	public boolean isOffline() {
		return this.offline;
	}
	
	public void setOffline() {
		this.offline = true;
	}
	
	public UserGuiManager getGuiManager() {
		return this.gui;
	}
	
	public UserSkillManager getSkillManager() {
		return this.skill;
	}
	
	public void resetAll() {
		if (this.gui != null) this.closeGui();
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
	
	public void openGui(Gui gui) {
		this.gui.open(gui);
	}
	
	public void closeGui() {
		this.gui.close(false);
	}
	
	public Gui getGui() {
		return this.gui.getGui();
	}
	
	public <T extends Gui> T getGui(Class<? extends T> type) {
		return this.gui.getGui(type);
	}
	
	public boolean hasGui() {
		return this.gui.hasGui();
	}
	
}
