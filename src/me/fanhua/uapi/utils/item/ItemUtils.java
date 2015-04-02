package me.fanhua.uapi.utils.item;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import me.fanhua.uapi.utils.ClassUtils;
import me.fanhua.uapi.utils.nbt.NBTTag;
import me.fanhua.uapi.utils.nbt.NBTTagCompound;

import org.bukkit.inventory.ItemStack;

public class ItemUtils {
	
	private static Class<?> clazCraft;
	private static Method methodToCraft;
	private static Field fieldHandle;
	private static Field fieldTag;
	
	static {
		try {
			ItemUtils.clazCraft = ClassUtils.getCraftClass("inventory.CraftItemStack");
			ItemUtils.methodToCraft = ClassUtils.getMethod(ItemUtils.clazCraft, "asCraftCopy", false, ItemStack.class);
			ItemUtils.fieldHandle = ClassUtils.getField(ItemUtils.clazCraft, "handle", true);
			ItemUtils.fieldTag = ClassUtils.getField(ItemUtils.fieldHandle.getType(), "tag", false);
		} catch (Throwable error) {
			error.printStackTrace();
		}
	}
	
	public static ItemStack toCraftItem(ItemStack item) {
		if (ItemUtils.clazCraft.isInstance(item)) return item;
		
		try {
			return (ItemStack) ItemUtils.methodToCraft.invoke(null, item);
		} catch (Throwable error) {
			error.printStackTrace();
			return null;
		}
	}
	
	private static Object getServerItem(ItemStack item) {
		try {
			return ItemUtils.fieldHandle.get(item);
		} catch (Throwable error) {
			error.printStackTrace();
			return null;
		}
	}
	
	public static NBTTagCompound getNBT(ItemStack item) {
		try {
			Object object = ItemUtils.fieldTag.get(ItemUtils.getServerItem(item));
			if (object == null) return new NBTTagCompound();
			return (NBTTagCompound) NBTTag.create(object);
		} catch (Throwable error) {
			error.printStackTrace();
			return null;
		}
	}
	
	public static void setNBT(ItemStack item, NBTTagCompound tag) {
		try {
			ItemUtils.fieldTag.set(ItemUtils.getServerItem(item), tag.getObject());
		} catch (Throwable error) {}
	}
	
}
