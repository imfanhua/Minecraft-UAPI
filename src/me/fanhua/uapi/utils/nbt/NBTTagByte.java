package me.fanhua.uapi.utils.nbt;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import me.fanhua.uapi.utils.ClassUtils;

public class NBTTagByte extends NBTTag {
	
	private static Class<?> clazzNBT;
	
	public static Class<?> getObjectClass() {
		return NBTTagByte.clazzNBT;
	}
	
	private static Constructor<?> constructorNBT;
	private static Method methodClone;
	private static Field fieldData;
	
	static {
		try {
			NBTTagByte.clazzNBT = ClassUtils.getServerClass("NBTTagByte");
			NBTTagByte.constructorNBT = ClassUtils.getConstructor(NBTTagByte.clazzNBT, true);
			NBTTagByte.methodClone = ClassUtils.getMethod(NBTTagByte.clazzNBT, "clone", false);
			NBTTagByte.fieldData = ClassUtils.getField(NBTTagByte.clazzNBT, "data", true);
		} catch (Throwable error) {}
	}
	
	public static NBTTagByte create(Object object) {
		NBTTagByte nbt = new NBTTagByte();
		nbt.object = object;
		return nbt;
	}
	
	private Object object;
	
	private NBTTagByte() {}
	
	public NBTTagByte(boolean data) {
		this((byte) (data ? 1 : 0));
	}
	
	public NBTTagByte(byte data) {
		try {
			this.object = NBTTagByte.constructorNBT.newInstance();
		} catch (Throwable error) {}
		
		this.set(data);
	}
	
	public void set(byte data) {
		try {
			NBTTagByte.fieldData.set(this.object, data);
		} catch (Throwable error) {}
	}
	
	public void set(boolean data) {
		this.set((byte) (data ? 1 : 0));
	}
	
	public byte get() {
		try {
			return (Byte) NBTTagByte.fieldData.get(this.object);
		} catch (Throwable error) {
			return 0;
		}
	}
	
	public boolean getBoolean() {
		return this.get() != 0;
	}
	
	public NBTTagByte clone() {
		try {
			return NBTTagByte.create(NBTTagByte.methodClone.invoke(this.object));
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
		return NBTType.BYTE;
	}

	@Override
	public NBTTag copy() {
		return this.clone();
	}
	
}
