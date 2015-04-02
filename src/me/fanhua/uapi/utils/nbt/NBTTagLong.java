package me.fanhua.uapi.utils.nbt;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import me.fanhua.uapi.utils.ClassUtils;

public class NBTTagLong extends NBTTag {
	
	private static Class<?> clazzNBT;
	
	public static Class<?> getObjectClass() {
		return NBTTagLong.clazzNBT;
	}
	
	private static Constructor<?> constructorNBT;
	private static Method methodClone;
	private static Field fieldData;
	
	static {
		try {
			NBTTagLong.clazzNBT = ClassUtils.getServerClass("NBTTagLong");
			NBTTagLong.constructorNBT = ClassUtils.getConstructor(NBTTagLong.clazzNBT, true);
			NBTTagLong.methodClone = ClassUtils.getMethod(NBTTagLong.clazzNBT, "clone", false);
			NBTTagLong.fieldData = ClassUtils.getField(NBTTagLong.clazzNBT, "data", true);
		} catch (Throwable error) {}
	}
	
	public static NBTTagLong create(Object object) {
		NBTTagLong nbt = new NBTTagLong();
		nbt.object = object;
		return nbt;
	}
	
	private Object object;
	
	private NBTTagLong() {}
	
	public NBTTagLong(long data) {
		try {
			this.object = NBTTagLong.constructorNBT.newInstance();
		} catch (Throwable error) {}
		
		this.set(data);
	}
	
	public void set(long data) {
		try {
			NBTTagLong.fieldData.set(this.object, data);
		} catch (Throwable error) {}
	}
	
	public long get() {
		try {
			return (Long) NBTTagLong.fieldData.get(this.object);
		} catch (Throwable error) {
			return 0;
		}
	}
	
	public NBTTagLong clone() {
		try {
			return NBTTagLong.create(NBTTagLong.methodClone.invoke(this.object));
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
		return NBTType.LONG;
	}

	@Override
	public NBTTag copy() {
		return this.clone();
	}
	
}
