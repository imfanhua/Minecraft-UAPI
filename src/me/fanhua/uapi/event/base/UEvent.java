package me.fanhua.uapi.event.base;

import java.util.ArrayList;
import java.util.List;

public class UEvent<T> {
	
	private List<UListener<T>> list;
	
	public UEvent() {
		this.list = new ArrayList<UListener<T>>();
	}
	
	public void add(UListener<T> listener) {
		if (this.has(listener)) return;
		this.list.add(listener);
	}
	
	public boolean remove(UListener<T> listener) {
		return this.list.remove(listener);
	}
	
	public boolean has(UListener<T> listener) {
		return this.list.contains(listener);
	}
	
	public void clear() {
		this.list.clear();
	}
	
	public List<UListener<T>> getAllListener() {
		return this.list;
	}
	
	public T call(T event) {
		for (UListener<T> listener : this.list) listener.call(event);
		return event;
	}
	
}

