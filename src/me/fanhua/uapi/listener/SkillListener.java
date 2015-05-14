package me.fanhua.uapi.listener;

import me.fanhua.uapi.api.API;
import me.fanhua.uapi.skill.Skill;
import me.fanhua.uapi.skill.event.SkillEventClickBlock;
import me.fanhua.uapi.skill.event.SkillEventClickEntity;
import me.fanhua.uapi.skill.event.SkillEventClick;
import me.fanhua.uapi.skill.event.SkillEventDrop;
import me.fanhua.uapi.skill.event.SkillEvent;
import me.fanhua.uapi.skill.event.SkillEventFlight;
import me.fanhua.uapi.skill.event.SkillEventPhysical;
import me.fanhua.uapi.skill.event.SkillEventSneak;
import me.fanhua.uapi.skill.event.SkillEventSprint;
import me.fanhua.uapi.skill.hanlder.SkillHanlder;
import me.fanhua.uapi.skill.render.ISkillSelectRender;
import me.fanhua.uapi.skill.render.SkillRender;
import me.fanhua.uapi.user.User;

import org.bukkit.block.Block;
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
	
	private SkillEvent call(SkillEvent event, SkillEvent object) {
		event.setCancelle(object.isCancelled());
		event.setContinue(object.isContinue());
		return this.call(event);
	}
	
	private SkillEvent call(SkillEvent event) {
		if (!event.isContinue()) return event;
		for (Skill skill : event.getUser().get(API.USER.SKILL).getSkills()) {
			SkillRender render = skill.getRender();
			if (render instanceof ISkillSelectRender) if (!(((ISkillSelectRender) render).isSelected())) continue;
			
			if (!this.call(skill, event)) return event;
		}
		
		return event;
	}
	
	private boolean call(Skill skill, SkillEvent event) {
		SkillHanlder hanlder = skill.getHanlder();
		if (hanlder != null) hanlder.call(event);
		return event.isContinue();
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerToggleSprintEvent(PlayerToggleSprintEvent event) {
		User user = API.to(event.getPlayer());
		if (user == null) return;
		if (this.call(new SkillEventSprint(user, event.isSprinting())).isCancelled()) event.setCancelled(true);
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerToggleFlightEvent(PlayerToggleFlightEvent event) {
		User user = API.to(event.getPlayer());
		if (user == null) return;
		if (this.call(new SkillEventFlight(user, event.isFlying())).isCancelled()) event.setCancelled(true);
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerToggleSneakEvent(PlayerToggleSneakEvent event) {
		User user = API.to(event.getPlayer());
		if (user == null) return;
		if (this.call(new SkillEventSneak(user, event.isSneaking())).isCancelled()) event.setCancelled(true);
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerInteractEvent(PlayerInteractEvent event) {
		User user = API.to(event.getPlayer());
		if (user == null) return;
		Action action = event.getAction();
		
		if (action == Action.PHYSICAL) {
			if (this.call(new SkillEventPhysical(user, event.getClickedBlock())).isCancelled()) event.setCancelled(true);
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
			
			if (this.call(new SkillEventClick(user, left), this.call(new SkillEventClickBlock(user, left, block))).isCancelled()) event.setCancelled(true);
		}
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerInteractEntityEvent(PlayerInteractEntityEvent event) {
		User user = API.to(event.getPlayer());
		if (user == null) return;
		if (this.call(new SkillEventClick(user, false), this.call(new SkillEventClickEntity(user, false, event.getRightClicked()))).isCancelled()) event.setCancelled(true);
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onEntityDamageByEntityEvent(EntityDamageByEntityEvent event) {
		User user = API.to(event.getDamager());
		if (user == null) return;
		if (this.call(new SkillEventClick(user, true), this.call(new SkillEventClickEntity(user, true, event.getEntity()))).isCancelled()) event.setCancelled(true);
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerDropItemEvent(PlayerDropItemEvent event) {
		User user = API.to(event.getPlayer());
		if (user == null) return;
		if (this.call(new SkillEventDrop(user, event.getItemDrop())).isCancelled()) event.setCancelled(true);
	}
	
}
