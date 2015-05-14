package me.fanhua.uapi.manager;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;

import me.fanhua.uapi.item.builder.ItemBuilder;
import me.fanhua.uapi.utils.ClassUtils;

public final class MapManager {
	
	private static MapManager instance = new MapManager();
	
	public static MapManager getInstance() {
		return MapManager.instance;
	}
	
	private Object maps;
	
	private Class<?> classWorldMap;
	
	private Method methodGetObject;
	private Method methodGetSize;
	
	private Field fieldMapView;
	private Field fieldWorldMap;
	private Field fieldId;
	
	private MapManager() {
		Object world = ClassUtils.getCraftClass("CraftWorld").cast(Bukkit.getWorlds().get(0));
		
		try {
			world = ClassUtils.getMethod(world.getClass(), "getHandle", false).invoke(world);
			this.maps = ClassUtils.getField(world.getClass(), "worldMaps", false).get(world);
		} catch (Throwable error) {
			error.printStackTrace();
		}
		
		this.classWorldMap = ClassUtils.getServerClass("WorldMap");
		
		Class<?> classCollection = this.maps.getClass();
		this.methodGetObject = ClassUtils.getMethod(classCollection, "get", false, Class.class, String.class);
		this.methodGetSize = ClassUtils.getMethod(classCollection, "a", false, String.class);
		
		this.fieldMapView = ClassUtils.getField(this.classWorldMap, "mapView", false);
		this.fieldWorldMap = ClassUtils.getField(ClassUtils.getCraftClass("map.CraftMapView"), "worldMap", true);
		this.fieldId = ClassUtils.getField(ClassUtils.getServerClass("PersistentBase"), "id", false);
	}
	
	public int newMapId() {
		try {
			return (int) this.methodGetSize.invoke(this.maps, "map");
		} catch (Throwable error) {
			error.printStackTrace();
			return -1;
		}
	}
	
	public MapView newMap() {
		return this.newMap(Bukkit.getWorlds().get(0));
	}
	
	public MapView newMap(World world) {
		return Bukkit.createMap(world);
	}
	
	public MapView newMap(int id) {
		return this.newMap(id, Bukkit.getWorlds().get(0));
	}
	
	public MapView newMap(int id, World world) {
		MapView view = this.getMap(id);
		while (true) {
			if (view != null) {
				int code = this.getMapId(view);
				if (code == -1) return null;
				else if (code >= id) return view;
			}
			
			view = this.newMap(world);
		}
	}
	
	public MapView getMap(int id) {
		try {
			return (MapView) this.fieldMapView.get(this.methodGetObject.invoke(this.maps, this.classWorldMap, "map_" + id));
		} catch (Throwable error) {
			error.printStackTrace();
			return null;
		}
	}
	
	public int getMapId(MapView view) {
		try {
			String text = (String) this.fieldId.get(this.fieldWorldMap.get(view));
			return Integer.parseInt(text.substring(4));
		} catch (Throwable error) {
			error.printStackTrace();
			return -1;
		}
	}
	
	public ItemBuilder getMapItem(int id) {
		return new ItemBuilder(358, id);
	}
	
	public ItemBuilder getMapItem(MapView view) {
		return this.getMapItem(this.getMapId(view));
	}
	
	public void clearRender(MapView view) {
		for (MapRenderer render : view.getRenderers()) view.removeRenderer(render);
	}
	
}
