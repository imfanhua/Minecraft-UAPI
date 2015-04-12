package me.fanhua.uapi.nbt;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import me.fanhua.uapi.utils.ClassUtils;

public class NBTTagInt extends NBTTag {
	
	private static Class<?> clazzNBT;
	
	public static Class<?> getObjectClass() {
		return NBTTagInt.clazzNBT;
	}
	
	private static Constructor<?> constructorNBT;
	private static Method methodClone;
	private static Field fieldData;
	
	static {
		try {
			NBTTagInt.clazzNBT = ClassUtils.getServerClass("NBTTagInt");
			NBTTagInt.constructorNBT = ClassUtils.getConstructor(NBTTagInt.clazzNBT, true);
			NBTTagInt.methodClone = ClassUtils.getMethod(NBTTagInt.clazzNBT, "clone", false);
			NBTTagInt.fieldData = ClassUtils.getField(NBTTagInt.clazzNBT, "data", true);
		} catch (Throwable error) {}
	}
	
	public static NBTTagInt create(Object object) {
		NBTTagInt nbt = new NBTTagInt();
		nbt.object = object;
		return nbt;
	}
	
	private Object object;
	
	private NBTTagInt() {}
	
	public NBTTagInt(int data) {
		try {
			this.object = NBTTagInt.constructorNBT.newInstance();
		} catch (Throwable error) {}
		
		this.set(data);
	}
	
	public void set(int data) {
		try {
			NBTTagInt.fieldData.set(this.object, data);
		} catch (Throwable error) {}
	}
	
	public int get() {
		try {
			return (Integer) NBTTagInt.fieldData.get(this.object);
		} catch (Throwable error) {
			return 0;
		}
	}
	
	public NBTTagInt clone() {
		try {
			return NBTTagInt.create(NBTTagInt.methodClone.invoke(this.object));
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
		return NBTType.INT;
	}

	@Override
	public NBTTag copy() {
		return this.clone();
	}
	
}
