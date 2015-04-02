package me.fanhua.uapi.utils.nbt;

import java.lang.reflect.Method;

import me.fanhua.uapi.utils.ClassUtils;

public enum NBTType {
	
	BYTE(1, "Byte", NBTTagByte.class),
	SHORT(2, "Short", NBTTagShort.class),
	INT(3, "Int", NBTTagShort.class),
	LONG(4, "Long", NBTTagLong.class),
	FLOAT(5, "Float", NBTTagFloat.class),
	DOUBLE(6, "Double", NBTTagDouble.class),
	ARRAYBYTE(7, "Byte[]", NBTTagArrayByte.class),
	STRING(8, "String", NBTTagString.class),
	LIST(9, "List", NBTTagList.class),
	COMPOUND(10, "Compound", NBTTagCompound.class),
	ARRAYINT(11, "Int[]", NBTTagArrayInt.class);
	
	private int id;
	private String name;
	private Class<? extends NBTTag> clazz;
	private Class<?> clazzNBT;
	private Method methodCreate;
	private Method methodRead;
	private Method methodWrite;
	
	private NBTType(int id, String name, Class<? extends NBTTag> clazz) {
		this.id = id;
		this.name = name;
		this.clazz = clazz;
		
		try {
			this.methodCreate = this.clazz.getMethod("create", clazz);
			
			this.clazzNBT = (Class<?>) clazz.getMethod("getObjectClass").invoke(null);
			this.methodRead = ClassUtils.getMethods(this.clazzNBT, "load", false, 3)[0];
			this.methodWrite = ClassUtils.getMethods(this.clazzNBT, "write", false, 1)[0];
		} catch (Throwable error) {}
	}
	
	public int getId() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public Class<? extends NBTTag> getTypeClass() {
		return this.clazz;
	}
	
	public Class<? extends NBTTag> getNBTClass() {
		return this.clazz;
	}
	
	public Method getCreateMethod() {
		return this.methodCreate;
	}
	
	public Method getReadMethod() {
		return this.methodRead;
	}
	
	public Method getWriteMethod() {
		return this.methodWrite;
	}
	
	public static NBTType getType(int id) {
		for (NBTType type : NBTType.values()) if (type.id == id) return type;
		return null;
	}
	
}
