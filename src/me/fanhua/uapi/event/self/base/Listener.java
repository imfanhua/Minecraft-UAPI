package me.fanhua.uapi.event.self.base;

public interface Listener<T> {
	
	public void call(T event);
	
}

