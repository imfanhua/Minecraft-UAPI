package me.fanhua.uapi.event.base.callable;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import me.fanhua.uapi.event.base.event.UEvent;

public final class UEventCallable implements IEventCallable {
	
	private Object instance;
	private Method method;
	
	private Class<? extends UEvent> event;
	
	public UEventCallable(Object instance, Method method, Class<? extends UEvent> event) {
		this.instance = instance;
		this.method = method;
		
		this.event = event;
	}
	
	public Method getMethod() {
		return this.method;
	}
	
	@Override
	public Class<? extends UEvent> getEvent() {
		return this.event;
	}
	
	@Override
	public void call(Object event) throws Throwable {
		try {
			this.method.invoke(this.instance, event);
		} catch (InvocationTargetException error) {
			throw error.getTargetException();
		}
	}
	
}
