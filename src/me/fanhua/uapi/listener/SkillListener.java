package me.fanhua.uapi.listener;

import me.fanhua.uapi.skill.Skill;
import me.fanhua.uapi.skill.event.SkillClickBlockEvent;
import me.fanhua.uapi.skill.event.SkillClickEntityEvent;
import me.fanhua.uapi.skill.event.SkillClickEvent;
import me.fanhua.uapi.skill.event.SkillDropEvent;
import me.fanhua.uapi.skill.event.SkillEvent;
import me.fanhua.uapi.skill.event.SkillFlightEvent;
import me.fanhua.uapi.skill.event.SkillPhysicalEvent;
import me.fanhua.uapi.skill.event.SkillSneakEvent;
import me.fanhua.uapi.skill.event.SkillSprintEvent;
import me.fanhua.uapi.skill.hanlder.SkillHanlder;
import me.fanhua.uapi.skill.render.ISkillSelectRender;
import me.fanhua.uapi.skill.render.SkillRender;
import me.fanhua.uapi.user.User;

import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.event.player.PlayerToggleSprintEvent;

public class SkillListener implements Listener {
	
	private void doEvent(User user, SkillEvent event) {
		for (Skill skill : user.getSkillManager().getSkills()) {
			SkillRender render = skill.getRender();
			if (render instanceof ISkillSelectRender) if (!(((ISkillSelectRender) render).isSelected())) continue;
			
			this.onSkillEvent(skill, event);
			if (!event.isContinue()) return;
		}
	}
	
	private void onSkillEvent(Skill skill, SkillEvent event) {
		SkillHanlder hanlder = skill.getHanlder();
		if (hanlder == null) return;
		hanlder.onEvent(event);
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerToggleSprintEvent(PlayerToggleSprintEvent event) {
		User user = User.toUser(event.getPlayer());
		if (user == null) return;
		SkillSprintEvent object = new SkillSprintEvent(user, event.isSprinting());
		
		this.doEvent(user, object);
		if (object.isCancelle()) event.setCancelled(true);
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerToggleFlightEvent(PlayerToggleFlightEvent event) {
		User user = User.toUser(event.getPlayer());
		if (user == null) return;
		SkillFlightEvent object = new SkillFlightEvent(user, event.isFlying());
		
		this.doEvent(user, object);
		if (object.isCancelle()) event.setCancelled(true);
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerToggleSneakEvent(PlayerToggleSneakEvent event) {
		User user = User.toUser(event.getPlayer());
		if (user == null) return;
		SkillSneakEvent object = new SkillSneakEvent(user, event.isSneaking());
		
		this.doEvent(user, object);
		if (object.isCancelle()) event.setCancelled(true);
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerInteractEvent(PlayerInteractEvent event) {
		User user = User.toUser(event.getPlayer());
		if (user == null) return;
		Action action = event.getAction();
		
		if (action == Action.PHYSICAL) {
			SkillPhysicalEvent object = new SkillPhysicalEvent(user, event.getClickedBlock());
			this.doEvent(user, object);
			
			if (object.isCancelle()) event.setCancelled(true);
		} else {
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
			
			SkillEvent object = new SkillClickBlockEvent(user, left, block);
			this.doEvent(user, object);
			
			if (object.isCancelle()) event.setCancelled(true);
			if (!object.isContinue()) return;
			
			object = new SkillClickEvent(user, left);
			this.doEvent(user, object);
			
			if (object.isCancelle()) event.setCancelled(true);
		}
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerInteractEntityEvent(PlayerInteractEntityEvent event) {
		User user = User.toUser(event.getPlayer());
		if (user == null) return;
		
		SkillEvent object = new SkillClickEntityEvent(user, false, event.getRightClicked());
		this.doEvent(user, object);
		
		if (object.isCancelle()) event.setCancelled(true);
		if (!object.isContinue()) return;
		
		object = new SkillClickEvent(user, false);
		this.doEvent(user, object);
		
		if (object.isCancelle()) event.setCancelled(true);
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onEntityDamageByEntityEvent(EntityDamageByEntityEvent event) {
		if (event.getDamager().getType() != EntityType.PLAYER) return;
		User user = User.toUser((Player) event.getDamager());
		if (user == null) return;
		
		SkillEvent object = new SkillClickEntityEvent(user, true, event.getEntity());
		this.doEvent(user, object);
		
		if (object.isCancelle()) event.setCancelled(true);
		if (!object.isContinue()) return;
		
		object = new SkillClickEvent(user, true);
		this.doEvent(user, object);
		
		if (object.isCancelle()) event.setCancelled(true);
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerDropItemEvent(PlayerDropItemEvent event) {
		User user = User.toUser(event.getPlayer());
		if (user == null) return;
		SkillDropEvent object = new SkillDropEvent(user, event.getItemDrop());
		
		this.doEvent(user, object);
		if (object.isCancelle()) event.setCancelled(true);
	}
	
}
