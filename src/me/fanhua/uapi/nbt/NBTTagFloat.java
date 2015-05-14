package me.fanhua.uapi.nbt;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import me.fanhua.uapi.utils.ClassUtils;

public final class NBTTagFloat extends NBTTag {
	
	private static Class<?> clazzNBT;
	
	public static Class<?> getObjectClass() {
		return NBTTagFloat.clazzNBT;
	}
	
	private static Constructor<?> constructorNBT;
	private static Method methodClone;
	private static Field fieldData;
	
	static {
		try {
			NBTTagFloat.clazzNBT = ClassUtils.getServerClass("NBTTagFloat");
			NBTTagFloat.constructorNBT = ClassUtils.getConstructor(NBTTagFloat.clazzNBT, true);
			NBTTagFloat.methodClone = ClassUtils.getMethod(NBTTagFloat.clazzNBT, "clone", false);
			NBTTagFloat.fieldData = ClassUtils.getField(NBTTagFloat.clazzNBT, "data", true);
		} catch (Throwable error) {}
	}
	
	public static NBTTagFloat create(Object object) {
		NBTTagFloat nbt = new NBTTagFloat();
		nbt.object = object;
		return nbt;
	}
	
	private Object object;
	
	private NBTTagFloat() {}
	
	public NBTTagFloat(float data) {
		try {
			this.object = NBTTagFloat.constructorNBT.newInstance();
		} catch (Throwable error) {}
		
		this.set(data);
	}
	
	public void set(float data) {
		try {
			NBTTagFloat.fieldData.set(this.object, data);
		} catch (Throwable error) {}
	}
	
	public float get() {
		try {
			return (Float) NBTTagFloat.fieldData.get(this.object);
		} catch (Throwable error) {
			return 0;
		}
	}
	
	public NBTTagFloat clone() {
		try {
			return NBTTagFloat.create(NBTTagFloat.methodClone.invoke(this.object));
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
		return NBTType.FLOAT;
	}

	@Override
	public NBTTag copy() {
		return this.clone();
	}
	
}
