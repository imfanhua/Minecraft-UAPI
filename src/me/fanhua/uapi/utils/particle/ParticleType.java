package me.fanhua.uapi.utils.particle;

import java.lang.reflect.Method;

import me.fanhua.uapi.utils.ClassUtils;

public class ParticleType {
	
	public static final ParticleType EXPLOSION_NORMAL = new ParticleType(0);
	public static final ParticleType EXPLOSION_LARGE = new ParticleType(1);
	public static final ParticleType EXPLOSION_HUGE = new ParticleType(2);
	public static final ParticleType FIREWORKS_SPARK = new ParticleType(3);
	public static final ParticleType WATER_BUBBLE = new ParticleType(4);
	public static final ParticleType WATER_SPLASH = new ParticleType(5);
	public static final ParticleType WATER_WAKE = new ParticleType(6);
	public static final ParticleType SUSPENDED = new ParticleType(7);
	public static final ParticleType SUSPENDED_DEPTH = new ParticleType(8);
	public static final ParticleType CRIT = new ParticleType(9);
	public static final ParticleType CRIT_MAGIC = new ParticleType(10);
	public static final ParticleType SMOKE_NORMAL = new ParticleType(11);
	public static final ParticleType SMOKE_LARGE = new ParticleType(12);
	public static final ParticleType SPELL = new ParticleType(13);
	public static final ParticleType SPELL_INSTANT = new ParticleType(14);
	public static final ParticleType SPELL_MOB = new ParticleType(15);
	public static final ParticleType SPELL_MOB_AMBIENT = new ParticleType(16);
	public static final ParticleType SPELL_WITCH = new ParticleType(17);
	public static final ParticleType DRIP_WATER = new ParticleType(18);
	public static final ParticleType DRIP_LAVA = new ParticleType(19);
	public static final ParticleType VILLAGER_ANGRY = new ParticleType(20);
	public static final ParticleType VILLAGER_HAPPY = new ParticleType(21);
	public static final ParticleType TOWN_AURA = new ParticleType(22);
	public static final ParticleType NOTE = new ParticleType(23);
	public static final ParticleType PORTAL = new ParticleType(24);
	public static final ParticleType ENCHANTMENT_TABLE = new ParticleType(25);
	public static final ParticleType FLAME = new ParticleType(26);
	public static final ParticleType LAVA = new ParticleType(27);
	public static final ParticleType FOOTSTEP = new ParticleType(28);
	public static final ParticleType CLOUD = new ParticleType(29);
	public static final ParticleType REDSTONE = new ParticleType(30);
	public static final ParticleType SNOWBALL = new ParticleType(31);
	public static final ParticleType SNOW_SHOVEL = new ParticleType(32);
	public static final ParticleType SLIME = new ParticleType(33);
	public static final ParticleType HEART = new ParticleType(34);
	public static final ParticleType BARRIER = new ParticleType(35);
	public static final ParticleType WATER_DROP = new ParticleType(39);
	public static final ParticleType ITEM_TAKE = new ParticleType(40);
	public static final ParticleType MOB_APPEARANCE = new ParticleType(41);
	
	public static final ParticleType ITEM_CRACK = new ParticleType(36);
	public static final ParticleType BLOCK_CRACK = new ParticleType(37);
	public static final ParticleType BLOCK_DUST = new ParticleType(38);
	
	private static Method method;
	
	static {
		ParticleType.method = ClassUtils.getMethod(ClassUtils.getServerClass("EnumParticle"), "a", false, int.class);
	}
	
	private int id;
	private Object object;
	
	private ParticleType(int id) {
		this.id = id;
		
		try {
			this.object = ParticleType.method.invoke(null, id);
		} catch (Throwable error) {}
	}
	
	public int getId() {
		return this.id;
	}
	
	public Object getEnumObject() {
		return this.object;
	}
	
}
