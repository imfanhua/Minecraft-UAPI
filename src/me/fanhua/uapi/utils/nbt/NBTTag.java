package me.fanhua.uapi.utils.nbt;

import java.lang.reflect.Method;

import me.fanhua.uapi.utils.ClassUtils;

public abstract class NBTTag {
	
	private static Method methodGetType;
	
	static {
		try {
			NBTTag.methodGetType = ClassUtils.getMethod(ClassUtils.getServerClass("NBTBase"), "getTypeId", false);
		} catch (Throwable error) {}
	}
	
	public static NBTTag create(Object object) {
		try {
			NBTType type = NBTType.getType((Integer) NBTTag.methodGetType.invoke(object));
			return (NBTTag) type.getCreateMethod().invoke(null, object);
		} catch (Throwable error) {
			return null;
		}
	}
	
	@Override
	public boolean equals(Object object) {
		if (object == null) return false;
		if (object instanceof NBTTag) object = ((NBTTag) object).getObject();
		return this.getObject().equals(object);
	}
	
	public abstract Object getObject();
	public abstract NBTType getType();
	public abstract NBTTag copy();
	
}
