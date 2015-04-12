package me.fanhua.uapi.nbt;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import me.fanhua.uapi.utils.ClassUtils;

public class NBTTagString extends NBTTag {
	
	private static Class<?> clazzNBT;
	
	public static Class<?> getObjectClass() {
		return NBTTagString.clazzNBT;
	}
	
	private static Constructor<?> constructorNBT;
	private static Method methodClone;
	private static Field fieldData;
	
	static {
		try {
			NBTTagString.clazzNBT = ClassUtils.getServerClass("NBTTagString");
			NBTTagString.constructorNBT = ClassUtils.getConstructor(NBTTagString.clazzNBT, true);
			NBTTagString.methodClone = ClassUtils.getMethod(NBTTagString.clazzNBT, "clone", false);
			NBTTagString.fieldData = ClassUtils.getField(NBTTagString.clazzNBT, "data", true);
		} catch (Throwable error) {}
	}
	
	public static NBTTagString create(Object object) {
		NBTTagString nbt = new NBTTagString();
		nbt.object = object;
		return nbt;
	}
	
	private Object object;
	
	private NBTTagString() {}
	
	public NBTTagString(String data) {
		try {
			this.object = NBTTagString.constructorNBT.newInstance();
		} catch (Throwable error) {}
		
		this.set(data);
	}
	
	public void set(String data) {
		try {
			NBTTagString.fieldData.set(this.object, data);
		} catch (Throwable error) {}
	}
	
	public String get() {
		try {
			return (String) NBTTagString.fieldData.get(this.object);
		} catch (Throwable error) {
			return null;
		}
	}
	
	public NBTTagString clone() {
		try {
			return NBTTagString.create(NBTTagString.methodClone.invoke(this.object));
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
		return NBTType.STRING;
	}

	@Override
	public NBTTag copy() {
		return this.clone();
	}
	
}
