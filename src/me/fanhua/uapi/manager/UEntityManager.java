package me.fanhua.uapi.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Location;

import me.fanhua.uapi.entity.UEntity;
import me.fanhua.uapi.rule.RuleUEntity;
import me.fanhua.uapi.user.User;

public final class UEntityManager {
	
	public static void newInstance() {
		RuleUEntity.RULE.setManager(new UEntityManager());
	}
	
	public static UEntityManager getInstance() {
		return RuleUEntity.RULE.getManager();
	}
	
	private Map<Integer, UEntity> entities;
	
	private UEntityManager() {
		this.entities = new HashMap<Integer, UEntity>();
	}
	
	public boolean hasEntity(UEntity entity) {
		return this.entities.containsKey(entity.getId());
	}
	
	public void spawn(UEntity entity) {
		if (this.hasEntity(entity)) return;
		this.entities.put(entity.getId(), entity);
		entity.onSpawn();
	}
	
	public void remove(UEntity entity) {
		if (!this.hasEntity(entity)) return;
		this.entities.remove(entity.getId());
		entity.onRemove();
	}
	
	public UEntity getEntity(int id) {
		return this.entities.get(id);
	}
	
	public List<UEntity> getEntities() {
		return new ArrayList<UEntity>(this.entities.values());
	}
	
	public <T extends UEntity> List<T> getEntities(Class<T> type) {
		List<T> entities = new ArrayList<T>();
		for (UEntity entity : this.entities.values()) if (type.isAssignableFrom(entity.getClass())) entities.add((T) entity);
		return entities;
	}
	
	@Deprecated
	public void clear() {
		for (UEntity entity : this.entities.values()) entity.onRemove();
		this.entities.clear();
	}
	
	@Deprecated
	public void onTick(int tick) {
		for (UEntity entity : this.entities.values()) entity.tick(tick);
	}
	
	@Deprecated
	public void onMove(User user, Location location, Location to) {
		for (UEntity entity : this.entities.values()) entity.onMove(user, location, to);
	}
	
}
