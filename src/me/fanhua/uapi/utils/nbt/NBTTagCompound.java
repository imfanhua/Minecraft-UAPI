package me.fanhua.uapi.utils.nbt;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;

import me.fanhua.uapi.utils.ClassUtils;

public class NBTTagCompound extends NBTTag {
	
	private static Class<?> clazzNBT;
	
	public static Class<?> getObjectClass() {
		return NBTTagCompound.clazzNBT;
	}
	
	private static Method methodClone;
	private static Field fieldMap;
	
	static {
		try {
			NBTTagCompound.clazzNBT = ClassUtils.getServerClass("NBTTagCompound");
			NBTTagCompound.methodClone = ClassUtils.getMethod(NBTTagCompound.clazzNBT, "clone", false);
			NBTTagCompound.fieldMap = ClassUtils.getField(NBTTagCompound.clazzNBT, "map", true);
		} catch (Throwable error) {}
	}
	
	public static NBTTagCompound create(Object object) {
		return new NBTTagCompound(object);
	}
	
	private Object object;
	
	private NBTTagCompound(Object object) {
		this.object = object;
	}
	
	public NBTTagCompound() {
		try {
			this.object = NBTTagCompound.clazzNBT.newInstance();
		} catch (Throwable error) {}
	}
	
	public void set(String key, NBTTag tag) {
		this.getMap().put(key, tag.getObject());
	}
	
	public NBTTag get(String key) {
		return NBTTag.create(this.getMap().get(key));
	}
	
	public <T extends NBTTag> T get(String key, Class<? extends T> target) {
		NBTTag tag = NBTTag.create(this.getMap().get(key));
		if (tag == null || !tag.getClass().getName().equals(target.getName())) return null;
		return (T) tag;
	}
	
	public NBTTag remove(String key) {
		return NBTTag.create(this.getMap().remove(key));
	}
	
	public boolean hasKey(String key) {
		return this.getMap().containsKey(key);
	}
	
	public String[] getKeyArray() {
		Set set = this.getMap().keySet();
		String[] array = new String[set.size()];
		int index = 0;
		for (Object object : set) array[index++] = (String) object;
		return array;
	}
	
	public String[] getArray() {
		Set set = this.getMap().keySet();
		String[] array = new String[set.size()];
		int index = 0;
		for (Object object : set) array[index++] = (String) object;
		return array;
	}
	
	public void clear() {
		this.getMap().clear();
	}
	
	public boolean isEmpty() {
		return this.getMap().isEmpty();
	}
	
	private Map getMap() {
		try {
			return (Map) NBTTagCompound.fieldMap.get(this.object);
		} catch (Throwable error) {
			return null;
		}
	}
	
	public NBTTagCompound clone() {
		try {
			return NBTTagCompound.create(NBTTagCompound.methodClone.invoke(this.object));
		} catch (Throwable error) {
			return null;
		}
	}
	
	@Override
	public Object getObject() {
		return this.object;
	}
	
	@Override
	public NBTType getType() {
		return NBTType.COMPOUND;
	}

	@Override
	public NBTTag copy() {
		return this.clone();
	}
	
}
