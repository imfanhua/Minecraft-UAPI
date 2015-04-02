package me.fanhua.uapi.utils.nbt;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import me.fanhua.uapi.utils.ClassUtils;

public class NBTTagShort extends NBTTag {
	
	private static Class<?> clazzNBT;
	
	public static Class<?> getObjectClass() {
		return NBTTagShort.clazzNBT;
	}
	
	private static Constructor<?> constructorNBT;
	private static Method methodClone;
	private static Field fieldData;
	
	static {
		try {
			NBTTagShort.clazzNBT = ClassUtils.getServerClass("NBTTagShort");
			NBTTagShort.constructorNBT = ClassUtils.getConstructor(NBTTagShort.clazzNBT, true);
			NBTTagShort.methodClone = ClassUtils.getMethod(NBTTagShort.clazzNBT, "clone", false);
			NBTTagShort.fieldData = ClassUtils.getField(NBTTagShort.clazzNBT, "data", true);
		} catch (Throwable error) {}
	}
	
	public static NBTTagShort create(Object object) {
		NBTTagShort nbt = new NBTTagShort();
		nbt.object = object;
		return nbt;
	}
	
	private Object object;
	
	private NBTTagShort() {}
	
	public NBTTagShort(short data) {
		try {
			this.object = NBTTagShort.constructorNBT.newInstance();
		} catch (Throwable error) {}
		
		this.set(data);
	}
	
	public void set(short data) {
		try {
			NBTTagShort.fieldData.set(this.object, data);
		} catch (Throwable error) {}
	}
	
	public short get() {
		try {
			return (Short) NBTTagShort.fieldData.get(this.object);
		} catch (Throwable error) {
			return 0;
		}
	}
	
	public NBTTagShort clone() {
		try {
			return NBTTagShort.create(NBTTagShort.methodClone.invoke(this.object));
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
		return NBTType.SHORT;
	}

	@Override
	public NBTTag copy() {
		return this.clone();
	}
	
}
