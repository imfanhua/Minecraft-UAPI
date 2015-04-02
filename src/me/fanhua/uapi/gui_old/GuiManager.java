package me.fanhua.uapi.gui_old;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GuiManager {
	
	private Map<Integer, Gui> map;
	
	public GuiManager() {
		this.map = new HashMap<Integer, Gui>();
	}
	
	public List<Gui> getAllScreen() {
		return new ArrayList<Gui>(this.map.values());
	}
	
	public Gui getScreen(int id) {
		return this.map.get(id);
	}
	
	public Gui newScreen(Gui screen) {
		this.map.put(screen.getId(), screen);
		return screen;
	}
	
	public void removeScreen(Gui screen) {
		if (screen == null) return;
		this.map.remove(screen.getId());
	}
	
	public void clear() {
		this.map.clear();
	}
	
}
