package me.fanhua.uapi.utils.nbt;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import me.fanhua.uapi.utils.ClassUtils;

public class NBTTagArrayInt extends NBTTag {
	
	private static Class<?> clazzNBT;
	
	public static Class<?> getObjectClass() {
		return NBTTagArrayInt.clazzNBT;
	}
	
	private static Constructor<?> constructorNBT;
	private static Method methodClone;
	private static Field fieldData;
	
	static {
		try {
			NBTTagArrayInt.clazzNBT = ClassUtils.getServerClass("NBTTagIntArray");
			NBTTagArrayInt.constructorNBT = ClassUtils.getConstructor(NBTTagArrayInt.clazzNBT, true);
			NBTTagArrayInt.methodClone = ClassUtils.getMethod(NBTTagArrayInt.clazzNBT, "clone", false);
			NBTTagArrayInt.fieldData = ClassUtils.getField(NBTTagArrayInt.clazzNBT, "data", true);
		} catch (Throwable error) {}
	}
	
	public static NBTTagArrayInt create(Object object) {
		return new NBTTagArrayInt(object);
	}
	
	private Object object;
	
	private NBTTagArrayInt(Object object) {
		this.object = object;
	}
	
	public NBTTagArrayInt() {
		try {
			this.object = NBTTagArrayInt.constructorNBT.newInstance();
		} catch (Throwable error) {}
	}
	
	public void set(int[] data) {
		try {
			NBTTagArrayInt.fieldData.set(this.object, data);
		} catch (Throwable error) {}
	}
	
	public void set(int index, int data) {
		try {
			int[] array = this.getArray();
			array[index] = data;
			NBTTagArrayInt.fieldData.set(this.object, array);
		} catch (Throwable error) {}
	}
	
	public int[] getArray() {
		try {
			return (int[]) NBTTagArrayInt.fieldData.get(this.object);
		} catch (Throwable error) {
			return null;
		}
	}
	
	public int get(int index) {
		try {
			return ((int[]) NBTTagArrayInt.fieldData.get(this.object))[index];
		} catch (Throwable error) {
			return 0;
		}
	}
	
	public NBTTagArrayInt clone() {
		try {
			return NBTTagArrayInt.create(NBTTagArrayInt.methodClone.invoke(this.object));
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
		return NBTType.ARRAYINT;
	}

	@Override
	public NBTTag copy() {
		return this.clone();
	}
	
}
