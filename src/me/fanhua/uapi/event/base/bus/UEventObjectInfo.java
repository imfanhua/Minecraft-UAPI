package me.fanhua.uapi.event.base.bus;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import me.fanhua.uapi.event.EventTag;
import me.fanhua.uapi.event.base.callable.IEventCallable;
import me.fanhua.uapi.event.base.callable.UEventCallable;
import me.fanhua.uapi.event.base.event.UEvent;

public final class UEventObjectInfo {
	
	private static Map<String, UEventObjectInfo> info = new HashMap<String, UEventObjectInfo>();
	
	public static UEventObjectInfo getInfo(Object object) {
		return UEventObjectInfo.getInfo(object.getClass());
	}
	
	public static UEventObjectInfo getInfo(Class<?> clazz) {
		UEventObjectInfo info = UEventObjectInfo.info.get(clazz.getName());
		if (info == null) {
			info = new UEventObjectInfo(clazz);
			UEventObjectInfo.info.put(clazz.getName(), info);
		}
		
		return info;
	}
	
	public static class MethodInfo {
		
		private static MethodInfo getInfo(Class<?> arg, Method method) {
			if (!UEvent.class.isAssignableFrom(arg)) return null;
			return new MethodInfo((Class<? extends UEvent>) arg, method);
		}
		
		private Class<? extends UEvent> event;
		private Method method;
		
		private boolean isStatic;
		
		private MethodInfo(Class<? extends UEvent> event, Method method) {
			this.event = event;
			this.method = method;
			
			this.isStatic = Modifier.isStatic(method.getModifiers());
		}
		
		public Class<? extends UEvent> getEvent() {
			return this.event;
		}
		
		public Method getMethod() {
			return this.method;
		}
		
		public IEventCallable newCallback(Object object) {
			return new UEventCallable(this.isStatic ? null : object, this.method, this.event);
		}
		
		public boolean isCallback(IEventCallable callback) {
			if (!(callback instanceof UEventCallable)) return false;
			return method.equals(((UEventCallable) callback).getMethod());
		}
		
	}
	
	public static class UnregisterInfo {
		
		private Class<? extends UEvent> event;
		private MethodInfo[] infos;
		
		private UnregisterInfo(Class<? extends UEvent> event, MethodInfo[] infos) {
			this.event = event;
			this.infos = infos;
		}
		
		public Class<? extends UEvent> getEvent() {
			return this.event;
		}
		
		public MethodInfo[] getInfos() {
			return this.infos;
		}
		
		public void unregister(UBus bus) {
			Iterator<IEventCallable> iterator = bus.getEvents(this.event).iterator();
			while (iterator.hasNext()) if (this.isCallback(iterator.next())) iterator.remove();
		}
		
		public boolean isCallback(IEventCallable callback) {
			for (MethodInfo info : this.infos) if (info.isCallback(callback)) return true;
			return false;
		}
		
	}
	
	private List<MethodInfo> infos;
	private UnregisterInfo[] unregisters;
	
	private UEventObjectInfo(Class<?> clazz) {
		this.infos = new ArrayList<MethodInfo>();
		
		for (Method method : clazz.getMethods()) {
			EventTag tag = method.getAnnotation(EventTag.class);
			if (tag == null) continue;
			Class<?>[] args = method.getParameterTypes();
			if (args.length != 1) continue;
			MethodInfo info = MethodInfo.getInfo(args[0], method);
			if (info != null) this.infos.add(info);
		}
	}
	
	public void register(UBus bus, Object object) {
		for (MethodInfo info : this.infos) bus.register(info.newCallback(object));
	}
	
	public void unregister(UBus bus) {
		if (this.unregisters == null) {
			Map<Class<? extends UEvent>, List<MethodInfo>> map = new HashMap<Class<? extends UEvent>, List<MethodInfo>>();
			for (MethodInfo info : this.infos) {
				List<MethodInfo> infos = map.get(info.getEvent());
				if (infos == null) {
					infos = new ArrayList<MethodInfo>();
					map.put(info.getEvent(), infos);
				}
				
				infos.add(info);
			}
			
			List<UnregisterInfo> unregisters = new ArrayList<UnregisterInfo>();
			for (Class<? extends UEvent> event : map.keySet()) {
				List<MethodInfo> infos = map.get(event);
				unregisters.add(new UnregisterInfo(event, infos.toArray(new MethodInfo[infos.size()])));
			}
			
			this.unregisters = unregisters.toArray(new UnregisterInfo[unregisters.size()]);
		}
		
		for (UnregisterInfo unregister : this.unregisters) unregister.unregister(bus);
	}
	
}
