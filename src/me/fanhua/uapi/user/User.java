package me.fanhua.uapi.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import me.fanhua.uapi.manager.UserManager;
import me.fanhua.uapi.network.Network;
import me.fanhua.uapi.network.packet.IPacket;
import me.fanhua.uapi.network.packet.Packet;
import me.fanhua.uapi.user.manager.base.IUserManager;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public final class User {
	
	public static User toUser(Player player) {
		if (!player.isOnline()) return null;
		
		User user = UserManager.getUser(player);
		if (user != null) {
			user.player = player;
			return user;
		}
		
		user = new User(player);
		UserManager.callRegisters(user);
		UserManager.setUser(user);
		return user;
	}
	
	private Player player;
	
	private Map<String, IUserManager> managers;
	
	private boolean offline;
	
	private User(Player player) {
		this.player = player;
		
		this.managers = new HashMap<String, IUserManager>();
	}
	
	public Player getPlayer() {
		return this.player;
	}
	
	public boolean isOffline() {
		if (this.offline) return true;
		if (!this.player.isOnline()) {
			this.setOffline();
			return true;
		}
		
		return false;
	}
	
	@Deprecated
	public void setOffline() {
		if (this.offline) return;
		this.offline = true;
		
		for (IUserManager manager : this.managers.values()) manager.onOffline();
	}
	
	public IUserManager[] getManagers() {
		return new ArrayList<IUserManager>(this.managers.values()).toArray(new IUserManager[this.managers.size()]);
	}
	
	public <T extends IUserManager> T getManager(Class<T> type) {
		return (T) this.managers.get(type.getName());
	}
	
	public <T extends IUserManager> T addManager(T manager) {
		Class<? extends IUserManager> type = manager.getClass();
		T object = (T) this.getManager(type);
		if (object != null) return object;
		this.managers.put(type.getName(), manager);
		return manager;
	}
	
	public String getName() {
		return this.player.getName();
	}
	
	public UUID getUID() {
		return this.player.getUniqueId();
	}
	
	public boolean isDead() {
		return this.player.isDead();
	}
	
	public World getWorld() {
		return this.player.getWorld();
	}
	
	public Location getLocation() {
		return this.player.getLocation();
	}
	
	public PlayerInventory getInventory() {
		return this.player.getInventory();
	}
	
	public GameMode getGameMode() {
		return this.player.getGameMode();
	}
	
	public boolean isGameMode(GameMode mode) {
		return this.player.getGameMode() == mode;
	}
	
	public int getHeldSlot() {
		return this.getInventory().getHeldItemSlot();
	}
	
	public boolean setHeldSlot(int slot) {
		return this.send(Packet.wrapper(Packet.OUT, "HeldItemSlot").write(slot));
	}
	
	public ItemStack getItemInHand() {
		return this.player.getItemInHand();
	}
	
	public void setItemInHand(ItemStack item) {
		this.player.setItemInHand(item);
	}
	
	public void clearInventory() {
		PlayerInventory inventory = this.player.getInventory();
		inventory.clear();
		inventory.setArmorContents(new ItemStack[4]);
	}
	
	public <T extends IUserManager> T get(Class<T> type) {
		return this.getManager(type);
	}
	
	public boolean send(IPacket packet) {
		return Network.send(this.player, packet.getPacketObject());
	}
	
}
