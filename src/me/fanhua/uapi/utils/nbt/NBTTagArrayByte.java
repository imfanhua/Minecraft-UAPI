package me.fanhua.uapi.utils.nbt;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import me.fanhua.uapi.utils.ClassUtils;

public class NBTTagArrayByte extends NBTTag {
	
	private static Class<?> clazzNBT;
	
	public static Class<?> getObjectClass() {
		return NBTTagArrayByte.clazzNBT;
	}
	
	private static Constructor<?> constructorNBT;
	private static Method methodClone;
	private static Field fieldData;
	
	static {
		try {
			NBTTagArrayByte.clazzNBT = ClassUtils.getServerClass("NBTTagIntByte");
			NBTTagArrayByte.constructorNBT = ClassUtils.getConstructor(NBTTagArrayByte.clazzNBT, true);
			NBTTagArrayByte.methodClone = ClassUtils.getMethod(NBTTagArrayByte.clazzNBT, "clone", false);
			NBTTagArrayByte.fieldData = ClassUtils.getField(NBTTagArrayByte.clazzNBT, "data", true);
		} catch (Throwable error) {}
	}
	
	public static NBTTagArrayByte create(Object object) {
		return new NBTTagArrayByte(object);
	}
	
	private Object object;
	
	private NBTTagArrayByte(Object object) {
		this.object = object;
	}
	
	public NBTTagArrayByte() {
		try {
			this.object = NBTTagArrayByte.constructorNBT.newInstance();
		} catch (Throwable error) {}
	}
	
	public void set(byte[] data) {
		try {
			NBTTagArrayByte.fieldData.set(this.object, data);
		} catch (Throwable error) {}
	}
	
	public void set(int index, byte data) {
		try {
			byte[] array = this.getArray();
			array[index] = data;
			NBTTagArrayByte.fieldData.set(this.object, array);
		} catch (Throwable error) {}
	}
	
	public byte[] getArray() {
		try {
			return (byte[]) NBTTagArrayByte.fieldData.get(this.object);
		} catch (Throwable error) {
			return null;
		}
	}
	
	public byte get(int index) {
		try {
			return ((byte[]) NBTTagArrayByte.fieldData.get(this.object))[index];
		} catch (Throwable error) {
			return 0;
		}
	}
	
	public NBTTagArrayByte clone() {
		try {
			return NBTTagArrayByte.create(NBTTagArrayByte.methodClone.invoke(this.object));
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
		return NBTType.ARRAYBYTE;
	}

	@Override
	public NBTTag copy() {
		return this.clone();
	}
	
}
