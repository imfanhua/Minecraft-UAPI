package me.fanhua.uapi.listener;

import me.fanhua.uapi.rule.RuleNoBreak;

import org.bukkit.GameMode;
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

public class BreakListener implements Listener {
	
	public BreakListener() {}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onBlockBreakEvent(BlockBreakEvent event) {
		if (!RuleNoBreak.RULE.check(event.getBlock().getLocation())) return;
		if (event.getPlayer().getGameMode() == GameMode.CREATIVE) return;
		event.setCancelled(true);
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerInteractEvent(PlayerInteractEvent event) {
		if (!RuleNoBreak.RULE.check(event.getPlayer())) return;
		if (event.getPlayer().getGameMode() == GameMode.CREATIVE) return;
		
		if (event.getAction() == Action.PHYSICAL || event.getAction() == Action.RIGHT_CLICK_BLOCK) if (!RuleNoBreak.RULE.check(event.getClickedBlock())) return;
		
		event.setCancelled(true);
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onEntityInteractEvent(EntityInteractEvent event) {
		if (!RuleNoBreak.RULE.check(event.getEntity())) return;
		event.setCancelled(true);
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerInteractEntityEvent(PlayerInteractEntityEvent event) {
		if (!RuleNoBreak.RULE.check(event.getPlayer())) return;
		if (event.getPlayer().getGameMode() == GameMode.CREATIVE) return;
		event.setCancelled(true);
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onHangingBreakByEntityEvent(HangingBreakByEntityEvent event) {
		if (!RuleNoBreak.RULE.check(event.getRemover())) return;
		if (event.getRemover().getType() == EntityType.PLAYER && ((Player) event.getRemover()).getGameMode() == GameMode.CREATIVE) return;
		event.setCancelled(true);
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onHangingPlaceEvent(HangingPlaceEvent event) {
		if (!RuleNoBreak.RULE.check(event.getPlayer())) return;
		if (event.getPlayer().getGameMode() == GameMode.CREATIVE) return;
		event.setCancelled(true);
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onInventoryClickEvent(InventoryClickEvent event) {
		if (!RuleNoBreak.RULE.check(event.getWhoClicked())) return;
		if (event.getWhoClicked().getGameMode() == GameMode.CREATIVE) return;
		event.setCancelled(true);
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerDropItemEvent(PlayerDropItemEvent event) {
		if (!RuleNoBreak.RULE.check(event.getPlayer())) return;
		event.setCancelled(true);
	}
	
}
