package me.fanhua.uapi.event.base;

public interface UListener<T> {
	
	public void call(T event);
	
}

