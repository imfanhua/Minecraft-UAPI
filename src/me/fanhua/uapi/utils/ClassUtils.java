package me.fanhua.uapi.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;

public class ClassUtils {
	
	private static String packageCraft;
	private static String packageServer;
	
	static {
		Object object = Bukkit.getServer();
		
		try {
			Class<?> clazz = object.getClass();
			ClassUtils.packageCraft = clazz.getPackage().getName();
			
			Method method = clazz.getMethod("getServer");
			ClassUtils.packageServer = method.getReturnType().getPackage().getName();
		} catch (Throwable error) {}
	}
	
	public static Class<?> getCraftClass(String name) {
		try {
			return Class.forName(ClassUtils.packageCraft + "." + name);
		} catch (Throwable error) {
			return null;
		}
	}
	
	public static Class<?> getServerClass(String name) {
		try {
			return Class.forName(ClassUtils.packageServer + "." + name);
		} catch (Throwable error) {
			return null;
		}
	}
	
	public static Field getField(Class<?> clazz, String name, boolean declared) {
		try {
			Field field = null;
			
			if (declared) {
				field = clazz.getDeclaredField(name);
				field.setAccessible(true);
			} else field = clazz.getField(name);
				
			return field;
		} catch (Throwable error) {
			return null;
		}
	}
	
	public static Method getMethod(Class<?> clazz, String name, boolean declared, Class<?>... args) {
		try {
			Method method = null;
			
			if (declared) {
				method = clazz.getDeclaredMethod(name, args);
				method.setAccessible(true);
			} else method = clazz.getMethod(name, args);
				
			return method;
		} catch (Throwable error) {
			return null;
		}
	}
	
	public static Method[] getMethods(Class<?> clazz, String name, int args) {
		try {
			List<Method> list = new ArrayList<Method>();
			
			for (Method method : clazz.getDeclaredMethods()) {
				if (method.getParameterTypes().length == args) {
					method.setAccessible(true);
					list.add(method);
				}
			}
			
			for (Method method : clazz.getDeclaredMethods()) if (method.getParameterTypes().length == args) list.add(method);
				
			return list.toArray(new Method[list.size()]);
		} catch (Throwable error) {
			return null;
		}
	}
	
	public static Method[] getMethods(Class<?> clazz, String name, boolean declared, int args) {
		try {
			List<Method> list = new ArrayList<Method>();
			
			if (declared) {
				for (Method method : clazz.getDeclaredMethods()) {
					if (method.getParameterTypes().length == args) {
						method.setAccessible(true);
						list.add(method);
					}
				}
			} else for (Method method : clazz.getDeclaredMethods()) if (method.getParameterTypes().length == args) list.add(method);
				
			return list.toArray(new Method[list.size()]);
		} catch (Throwable error) {
			return null;
		}
	}
	
	public static Method[] getMethods(Class<?> clazz, String name, Class<?> result) {
		try {
			List<Method> list = new ArrayList<Method>();
			
			for (Method method : clazz.getDeclaredMethods()) {
				if (method.getReturnType().getName().equals(result.getName())) {
					method.setAccessible(true);
					list.add(method);
				}
			}
			
			for (Method method : clazz.getDeclaredMethods()) if (method.getReturnType().getName().equals(result.getName())) list.add(method);
				
			return list.toArray(new Method[list.size()]);
		} catch (Throwable error) {
			return null;
		}
	}
	
	public static Method[] getMethods(Class<?> clazz, String name, boolean declared, Class<?> result) {
		try {
			List<Method> list = new ArrayList<Method>();
			
			if (declared) {
				for (Method method : clazz.getDeclaredMethods()) {
					if (method.getReturnType().getName().equals(result.getName())) {
						method.setAccessible(true);
						list.add(method);
					}
				}
			} else for (Method method : clazz.getDeclaredMethods()) if (method.getReturnType().getName().equals(result.getName())) list.add(method);
				
			return list.toArray(new Method[list.size()]);
		} catch (Throwable error) {
			return null;
		}
	}
	
	public static Constructor<?> getConstructor(Class<?> clazz, boolean declared, Class<?>... args) {
		try {
			Constructor<?> constructor = null;
			
			if (declared) {
				constructor = clazz.getDeclaredConstructor(args);
				constructor.setAccessible(true);
			} else constructor = clazz.getConstructor(args);
				
			return constructor;
		} catch (Throwable error) {
			return null;
		}
	}
	
	public static Constructor<?>[] getConstructors(Class<?> clazz, int args) {
		try {
			List<Constructor<?>> list = new ArrayList<Constructor<?>>();
			
			for (Constructor<?> constructor : clazz.getDeclaredConstructors()) {
				if (constructor.getParameterTypes().length == args) {
					constructor.setAccessible(true);
					list.add(constructor);
				}
			}
			
			for (Constructor<?> constructor : clazz.getDeclaredConstructors()) if (constructor.getParameterTypes().length == args) list.add(constructor);
				
			return list.toArray(new Constructor<?>[list.size()]);
		} catch (Throwable error) {
			return null;
		}
	}
	
}
