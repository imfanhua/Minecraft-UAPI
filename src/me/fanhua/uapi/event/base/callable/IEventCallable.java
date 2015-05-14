package me.fanhua.uapi.event.base.callable;

import me.fanhua.uapi.event.base.event.UEvent;

public interface IEventCallable {
	
	public Class<? extends UEvent> getEvent();
	
	public void call(Object event) throws Throwable;
	
}
