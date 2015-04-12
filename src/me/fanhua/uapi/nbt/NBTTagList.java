package me.fanhua.uapi.nbt;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import me.fanhua.uapi.utils.ClassUtils;

public class NBTTagList extends NBTTag {
	
	private static Class<?> clazzNBT;
	
	public static Class<?> getObjectClass() {
		return NBTTagList.clazzNBT;
	}
	
	private static Method methodClone;
	private static Field fieldList;
	
	static {
		try {
			NBTTagList.clazzNBT = ClassUtils.getServerClass("NBTTagList");
			NBTTagList.methodClone = ClassUtils.getMethod(NBTTagList.clazzNBT, "clone", false);
			NBTTagList.fieldList = ClassUtils.getField(NBTTagList.clazzNBT, "list", true);
		} catch (Throwable error) {}
	}
	
	public static NBTTagList create(Object object) {
		return new NBTTagList(object);
	}
	
	private Object object;
	
	private NBTTagList(Object object) {
		this.object = object;
	}
	
	public NBTTagList() {
		try {
			this.object = NBTTagList.clazzNBT.newInstance();
		} catch (Throwable error) {}
	}
	
	public void add(NBTTag tag) {
		this.getList().add(tag.getObject());
	}
	
	public void add(int index, NBTTag tag) {
		this.getList().add(index, tag.getObject());
	}
	
	public NBTTag get(int index) {
		return NBTTag.create(this.getList().get(index));
	}
	
	public NBTTag remove(int index) {
		return NBTTag.create(this.getList().remove(index));
	}
	
	public void clear() {
		this.getList().clear();
	}
	
	public int size() {
		return this.getList().size();
	}
	
	public NBTTag[] toArray() {
		List list = this.getList();
		NBTTag[] array = new NBTTag[list.size()];
		int index = 0;
		for (Object object : list) array[index++] = NBTTag.create(object);
		return array;
	}
	
	public boolean isEmpty() {
		return this.getList().isEmpty();
	}
	
	private List getList() {
		try {
			return (List) NBTTagList.fieldList.get(this.object);
		} catch (Throwable error) {
			return null;
		}
	}
	
	public NBTTagList clone() {
		try {
			return NBTTagList.create(NBTTagList.methodClone.invoke(this.object));
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
		return NBTType.LIST;
	}

	@Override
	public NBTTag copy() {
		return this.clone();
	}
	
}
