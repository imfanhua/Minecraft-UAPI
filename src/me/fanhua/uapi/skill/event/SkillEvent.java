package me.fanhua.uapi.skill.event;

import me.fanhua.uapi.user.User;

import org.bukkit.entity.Player;

public abstract class SkillEvent {
	
	private User user;
	private boolean isContinue;
	private boolean isCancelle;
	
	public SkillEvent(User user) {
		this.user = user;
		this.isContinue = true;
	}
	
	public User getUser() {
		return this.user;
	}
	
	public Player getPlayer() {
		return this.user.getPlayer();
	}
	
	public boolean isContinue() {
		return this.isContinue;
	}
	
	public void setContinue(boolean isContinue) {
		this.isContinue = isContinue;
	}
	
	public boolean isCancelle() {
		return this.isCancelle;
	}
	
	public void setCancelle(boolean isCancelle) {
		this.isCancelle = isCancelle;
	}
	
}
