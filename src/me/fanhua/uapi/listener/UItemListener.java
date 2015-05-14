package me.fanhua.uapi.listener;

import me.fanhua.uapi.api.API;
import me.fanhua.uapi.item.UItem;
import me.fanhua.uapi.item.event.UItemEvent;
import me.fanhua.uapi.item.event.UItemEventClick;
import me.fanhua.uapi.item.event.UItemEventClickBlock;
import me.fanhua.uapi.item.event.UItemEventClickEntity;
import me.fanhua.uapi.item.event.UItemEventDrop;
import me.fanhua.uapi.item.event.UItemEventPlace;
import me.fanhua.uapi.manager.UItemManager;
import me.fanhua.uapi.user.User;

import org.bukkit.block.Block;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class UItemListener implements Listener {
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onBlockPlaceEvent(BlockPlaceEvent event) {
		User user = API.to(event.getPlayer());
		if (user == null) return;
		ItemStack item = user.getItemInHand();
		UItem object = UItemManager.getInstance().getItem(item);
		if (object == null) return;
		
		UItemEvent result = object.call(new UItemEventPlace(user, object, item, event.getBlock(), event.getBlockPlaced()));
		if (result.isCancelled()) {
			user.setItemInHand(result.getItem());
			event.setCancelled(true);
		}
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerInteractEvent(PlayerInteractEvent event) {
		User user = API.to(event.getPlayer());
		if (user == null) return;
		Action action = event.getAction();
		if (action == Action.PHYSICAL) return;
		ItemStack item = user.getItemInHand();
		UItem object = UItemManager.getInstance().getItem(item);
		if (object == null) return;
		boolean left = false;
		Block block = null;
		
		if (event.getAction() == Action.LEFT_CLICK_AIR) left = true;
		else if (event.getAction() == Action.LEFT_CLICK_BLOCK) {
			left = true;
			block = event.getClickedBlock();
		} else if (event.getAction() == Action.RIGHT_CLICK_AIR) left = false;
		else if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
			left = false;
			block = event.getClickedBlock();
		}
		
		UItemEvent result = object.call(new UItemEventClick(user, object, item, left), object.call(new UItemEventClickBlock(user, object, item, left, block)));
		if (result.isCancelled()) {
			user.setItemInHand(result.getItem());
			event.setCancelled(true);
		}
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerInteractEntityEvent(PlayerInteractEntityEvent event) {
		User user = API.to(event.getPlayer());
		if (user == null) return;
		ItemStack item = user.getItemInHand();
		UItem object = UItemManager.getInstance().getItem(item);
		if (object == null) return;
		
		UItemEvent result = 
				object.call(new UItemEventClick(user, object, item, false),
				object.call(new UItemEventClickEntity(user, object, item, false, event.getRightClicked()))
				);
		if (result.isCancelled()) {
			user.setItemInHand(result.getItem());
			event.setCancelled(true);
		}
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onEntityDamageByEntityEvent(EntityDamageByEntityEvent event) {
		User user = API.to(event.getDamager());
		if (user == null) return;
		ItemStack item = user.getItemInHand();
		UItem object = UItemManager.getInstance().getItem(item);
		if (object == null) return;
		
		UItemEvent result = 
				object.call(new UItemEventClick(user, object, item, true),
				object.call(new UItemEventClickEntity(user, object, item, true, event.getEntity()))
				);
		if (result.isCancelled()) {
			user.setItemInHand(result.getItem());
			event.setCancelled(true);
		}
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerDropItemEvent(PlayerDropItemEvent event) {
		User user = API.to(event.getPlayer());
		if (user == null) return;
		Item entity = event.getItemDrop();
		ItemStack item = entity.getItemStack().clone();
		UItem object = UItemManager.getInstance().getItem(item);
		if (object == null) return;
		
		UItemEvent result = object.call(new UItemEventDrop(user, object, item));
		if (result.isCancelled()) event.setCancelled(true);
		item = result.getItem();
		if (item == null) entity.remove();
		else entity.setItemStack(item.clone());
	}
	
}
