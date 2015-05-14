package me.fanhua.uapi.event.base.bus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.fanhua.uapi.event.base.callable.IEventCallable;
import me.fanhua.uapi.event.base.event.IEventContinuable;
import me.fanhua.uapi.event.base.event.UEvent;

public final class UBus {
	
	public static Throwable report(Throwable error) {
		if (error != null) error.printStackTrace();
		return error;
	}
	
	private Map<String, List<IEventCallable>> events;
	
	public UBus() {
		this.events = new HashMap<String, List<IEventCallable>>();
	}
	
	public List<IEventCallable> getEvents(Class<? extends UEvent> event) {
		List<IEventCallable> events = this.events.get(event.getName());
		if (events == null) {
			events = new ArrayList<IEventCallable>();
			this.events.put(event.getName(), events);
		}
		
		return events;
	}
	
	public void clear(Class<? extends UEvent> event) {
		this.events.remove(event.getName());
	}
	
	public void clear() {
		this.events = new HashMap<String, List<IEventCallable>>();
	}
	
	public void register(IEventCallable callable) {
		this.getEvents(callable.getEvent()).add(callable);
	}
	
	public void register(Object object) {
		UEventObjectInfo.getInfo(object).register(this, object);
	}
	
	public void unregister(IEventCallable callable) {
		this.getEvents(callable.getEvent()).remove(callable);
	}
	
	public void unregister(Object object) {
		UEventObjectInfo.getInfo(object).unregister(this);
	}
	
	public <T extends UEvent> T fire(T event) throws Throwable {
		List<IEventCallable> callbacks = this.getEvents(event.getClass());
		if (event instanceof IEventContinuable) this.run(callbacks, (IEventContinuable) event);
		else this.run(callbacks, event);
		
		return event;
	}
	
	private void run(List<IEventCallable> callbacks, UEvent event) throws Throwable {
		for (IEventCallable callback : callbacks) callback.call(event);
	}
	
	private void run(List<IEventCallable> callbacks, IEventContinuable event) throws Throwable {
		if (!event.isContinue()) return;
		
		for (IEventCallable callback : callbacks) {
			callback.call(event);
			if (!event.isContinue()) return;
		}
	}
	
	public Throwable call(UEvent event) {
		try {
			this.fire(event);
			return null;
		} catch (Throwable error) {
			return error;
		}
	}
	
}
