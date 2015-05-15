package me.fanhua.uapi.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import me.fanhua.uapi.nbt.NBTTag;
import me.fanhua.uapi.nbt.NBTTagCompound;

import org.bukkit.inventory.ItemStack;

public class ItemUtils {
	
	private static Class<?> classCraft;
	private static Method methodToCraft;
	private static Method methodToMirror;
	private static Field fieldHandle;
	private static Field fieldTag;
	
	static {
		try {
			ItemUtils.classCraft = ClassUtils.getCraftClass("inventory.CraftItemStack");
			ItemUtils.methodToCraft = ClassUtils.getMethod(ItemUtils.classCraft, "asNMSCopy", false, ItemStack.class);
			ItemUtils.fieldHandle = ClassUtils.getField(ItemUtils.classCraft, "handle", true);
			ItemUtils.fieldTag = ClassUtils.getField(ItemUtils.fieldHandle.getType(), "tag", true);
			ItemUtils.methodToMirror = ClassUtils.getMethod(ItemUtils.classCraft, "asCraftMirror", false, ItemUtils.fieldHandle.getType());
		} catch (Throwable error) {}
	}
	
	public static ItemStack toCraftItem(ItemStack item) {
		if (ItemUtils.classCraft.isInstance(item)) return item;
		
		try {
			return (ItemStack) ItemUtils.methodToMirror.invoke(null, ItemUtils.methodToCraft.invoke(null, item));
		} catch (Throwable error) {
			return null;
		}
	}
	
	private static Object getServerItem(ItemStack item) {
		try {
			return ItemUtils.fieldHandle.get(item);
		} catch (Throwable error) {
			return null;
		}
	}
	
	public static NBTTagCompound getNBT(ItemStack item) {
		try {
			Object object = ItemUtils.fieldTag.get(ItemUtils.getServerItem(ItemUtils.toCraftItem(item)));
			if (object == null) return new NBTTagCompound();
			return (NBTTagCompound) NBTTag.create(object);
		} catch (Throwable error) {
			return new NBTTagCompound();
		}
	}
	
	public static void setNBT(ItemStack item, NBTTagCompound tag) {
		try {
			ItemUtils.fieldTag.set(ItemUtils.getServerItem(item), tag.getObject());
		} catch (Throwable error) {}
	}
	
	public static ItemStack setCraftNBT(ItemStack item, NBTTagCompound tag) {
		try {
			item = ItemUtils.toCraftItem(item);
			ItemUtils.fieldTag.set(ItemUtils.getServerItem(item), tag.clone().getObject());
			return item;
		} catch (Throwable error) {
			return item;
		}
	}
	
}
