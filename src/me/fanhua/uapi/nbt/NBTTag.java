package me.fanhua.uapi.nbt;

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
		if (object == null) return null;
		
		try {
			NBTType type = NBTType.getType((byte) NBTTag.methodGetType.invoke(object));
			return (NBTTag) type.getCreateMethod().invoke(null, object);
		} catch (Throwable error) {
			return null;
		}
	}
	
	@Override
    public int hashCode() {
        return this.getObject().hashCode();
    }
	
	@Override
	public boolean equals(Object object) {
		if (object == null) return false;
		if (object instanceof NBTTag) object = ((NBTTag) object).getObject();
		return this.getObject().equals(object);
	}
	
	@Override
	public String toString() {
		return this.getObject().toString();
	}
	
	public abstract Object getObject();
	public abstract NBTType getType();
	public abstract NBTTag copy();
	
}
