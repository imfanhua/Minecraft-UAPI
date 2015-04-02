package me.fanhua.uapi.listener;

import org.bukkit.GameMode;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityInteractEvent;
import org.bukkit.event.hanging.HangingBreakByEntityEvent;
import org.bukkit.event.hanging.HangingPlaceEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class NoBreakListener implements Listener {
	
	private static int[] blocks = new int[] { 70, 72, 77, 143, 69, 147, 148 };
	
	private String world;
	
	public NoBreakListener() {
		this(null);
	}
	
	public NoBreakListener(String world) {
		if (world != null) this.world = world.toLowerCase();
	}
	
	private boolean isWorld(Block block) {
		return this.isWorld(block.getWorld());
	}
	
	private boolean isWorld(Entity entity) {
		return this.isWorld(entity.getWorld());
	}
	
	private boolean isWorld(World world) {
		if (this.world == null) return true;
		return world.getName().toLowerCase().equals(this.world);
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onBlockBreakEvent(BlockBreakEvent event) {
		if (!this.isWorld(event.getBlock())) return;
		if (event.getPlayer().getGameMode() == GameMode.CREATIVE) return;
		event.setCancelled(true);
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerInteractEvent(PlayerInteractEvent event) {
		if (!this.isWorld(event.getPlayer())) return;
		if (event.getPlayer().getGameMode() == GameMode.CREATIVE) return;
		
		if (event.getAction() == Action.PHYSICAL || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
			int id = event.getClickedBlock().getTypeId();
			for (int block : NoBreakListener.blocks) if (id == block) return;
		}
		
		event.setCancelled(true);
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onEntityInteractEvent(EntityInteractEvent event) {
		if (!this.isWorld(event.getEntity())) return;
		event.setCancelled(true);
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerInteractEntityEvent(PlayerInteractEntityEvent event) {
		if (!this.isWorld(event.getPlayer())) return;
		if (event.getPlayer().getGameMode() == GameMode.CREATIVE) return;
		event.setCancelled(true);
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onHangingBreakByEntityEvent(HangingBreakByEntityEvent event) {
		if (!this.isWorld(event.getRemover())) return;
		if (event.getRemover().getType() == EntityType.PLAYER && ((Player) event.getRemover()).getGameMode() == GameMode.CREATIVE) return;
		event.setCancelled(true);
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onHangingPlaceEvent(HangingPlaceEvent event) {
		if (!this.isWorld(event.getPlayer())) return;
		if (event.getPlayer().getGameMode() == GameMode.CREATIVE) return;
		event.setCancelled(true);
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onInventoryClickEvent(InventoryClickEvent event) {
		if (!this.isWorld(event.getWhoClicked())) return;
		if (event.getWhoClicked().getGameMode() == GameMode.CREATIVE) return;
		event.setCancelled(true);
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerDropItemEvent(PlayerDropItemEvent event) {
		if (!this.isWorld(event.getPlayer())) return;
		event.setCancelled(true);
	}
	
}
