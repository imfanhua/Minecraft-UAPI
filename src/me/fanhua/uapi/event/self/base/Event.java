package me.fanhua.uapi.event.self.base;

import java.util.ArrayList;
import java.util.List;

public class Event<T> {
	
	private List<Listener<T>> list;
	
	public Event() {
		this.list = new ArrayList<Listener<T>>();
	}
	
	public void add(Listener<T> listener) {
		if (this.has(listener)) return;
		this.list.add(listener);
	}
	
	public boolean remove(Listener<T> listener) {
		return this.list.remove(listener);
	}
	
	public boolean has(Listener<T> listener) {
		return this.list.contains(listener);
	}
	
	public void clear() {
		this.list.clear();
	}
	
	public List<Listener<T>> getAllListener() {
		return this.list;
	}
	
	public T call(T event) {
		for (Listener<T> listener : this.list) listener.call(event);
		return event;
	}
	
}

