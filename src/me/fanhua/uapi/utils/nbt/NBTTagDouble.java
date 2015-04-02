package me.fanhua.uapi.utils.nbt;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import me.fanhua.uapi.utils.ClassUtils;

public class NBTTagDouble extends NBTTag {
	
	private static Class<?> clazzNBT;
	
	public static Class<?> getObjectClass() {
		return NBTTagDouble.clazzNBT;
	}
	
	private static Constructor<?> constructorNBT;
	private static Method methodClone;
	private static Field fieldData;
	
	static {
		try {
			NBTTagDouble.clazzNBT = ClassUtils.getServerClass("NBTTagDouble");
			NBTTagDouble.constructorNBT = ClassUtils.getConstructor(NBTTagDouble.clazzNBT, true);
			NBTTagDouble.methodClone = ClassUtils.getMethod(NBTTagDouble.clazzNBT, "clone", false);
			NBTTagDouble.fieldData = ClassUtils.getField(NBTTagDouble.clazzNBT, "data", true);
		} catch (Throwable error) {}
	}
	
	public static NBTTagDouble create(Object object) {
		NBTTagDouble nbt = new NBTTagDouble();
		nbt.object = object;
		return nbt;
	}
	
	private Object object;
	
	private NBTTagDouble() {}
	
	public NBTTagDouble(double data) {
		try {
			this.object = NBTTagDouble.constructorNBT.newInstance();
		} catch (Throwable error) {}
		
		this.set(data);
	}
	
	public void set(double data) {
		try {
			NBTTagDouble.fieldData.set(this.object, data);
		} catch (Throwable error) {}
	}
	
	public double get() {
		try {
			return (Double) NBTTagDouble.fieldData.get(this.object);
		} catch (Throwable error) {
			return 0;
		}
	}
	
	public NBTTagDouble clone() {
		try {
			return NBTTagDouble.create(NBTTagDouble.methodClone.invoke(this.object));
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
		return NBTType.DOUBLE;
	}

	@Override
	public NBTTag copy() {
		return this.clone();
	}
	
}
