package me.fanhua.uapi.nbt;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import me.fanhua.uapi.utils.ClassUtils;

public final class NBT {
	
	public final static Class<NBTTagCompound> MAP = NBTTagCompound.class;
	public final static Class<NBTTagCompound> COMPOUBD = NBTTagCompound.class;
	public final static Class<NBTTagList> LIST = NBTTagList.class;
	
	public final static Class<NBTTagByte> BYTE = NBTTagByte.class;
	public final static Class<NBTTagShort> SHORT = NBTTagShort.class;
	public final static Class<NBTTagInt> INT = NBTTagInt.class;
	public final static Class<NBTTagLong> LONG = NBTTagLong.class;
	public final static Class<NBTTagFloat> FLOAT = NBTTagFloat.class;
	public final static Class<NBTTagDouble> DOUBLE = NBTTagDouble.class;
	public final static Class<NBTTagString> STRING = NBTTagString.class;
	
	public final static Class<NBTTagArrayByte> ARRAY_BYTE = NBTTagArrayByte.class;
	public final static Class<NBTTagArrayInt> ARRAY_INT = NBTTagArrayInt.class;
	
	private static Object nbtLimiter;
	
	static {
		try {
			NBT.nbtLimiter = ClassUtils.getServerClass("NBTReadLimiterUnlimited").newInstance();
		} catch (Throwable error) {}
	}
	
	public static byte[] write(NBTTag tag) throws Throwable {
		ByteArrayOutputStream bytes = new ByteArrayOutputStream();
		DataOutputStream output = new DataOutputStream(new BufferedOutputStream(new GZIPOutputStream(bytes)));
		
		if (tag == null) output.writeByte(0);
		else {
			NBTType type = tag.getType();
			output.writeByte(type.getId());
			output.writeUTF("");
			
			type.getWriteMethod().invoke(tag.getObject(), output);
		}
		
		return bytes.toByteArray();
	}
	
	public static NBTTag read(byte[] bytes) throws Throwable {
		DataInputStream input = new DataInputStream(new BufferedInputStream(new GZIPInputStream(new ByteArrayInputStream(bytes))));
		
		int id = input.readByte();
		if (id == 0) return null;
		input.readUTF();
		
		NBTType type = NBTType.getType(id);
		Object object = type.getNBTClass().newInstance();
		
		type.getReadMethod().invoke(object, input, 0, NBT.nbtLimiter);
		return NBTTag.create(object);
	}
	
}
