package me.fanhua.uapi.utils.particle;

import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

public class Particle {
	
	public static final Particle ExplosionNormal = new Particle(ParticleType.EXPLOSION_NORMAL);
	public static final Particle ExplosionLarge = new Particle(ParticleType.EXPLOSION_LARGE);
	public static final Particle ExplosionHuge = new Particle(ParticleType.EXPLOSION_LARGE);
	public static final Particle FireworksSpark = new Particle(ParticleType.FIREWORKS_SPARK);
	public static final Particle WaterBubble = new Particle(ParticleType.WATER_BUBBLE);
	public static final Particle WaterSplash = new Particle(ParticleType.WATER_SPLASH);
	public static final Particle WaterWake = new Particle(ParticleType.WATER_DROP);
	public static final Particle WaterDrop = new Particle(ParticleType.WATER_DROP);
	public static final Particle Suspended = new Particle(ParticleType.SUSPENDED);
	public static final Particle SuspendedDepth = new Particle(ParticleType.SUSPENDED_DEPTH);
	public static final Particle Crit = new Particle(ParticleType.CRIT);
	public static final Particle CritMagic = new Particle(ParticleType.CRIT_MAGIC);
	public static final Particle SmokeNormal = new Particle(ParticleType.SMOKE_NORMAL);
	public static final Particle SmokeLarge = new Particle(ParticleType.SMOKE_LARGE);
	public static final Particle Spell = new Particle(ParticleType.SPELL);
	public static final Particle SpellInstant = new Particle(ParticleType.SPELL_INSTANT);
	public static final Particle SpellMob = new Particle(ParticleType.SPELL_MOB);
	public static final Particle SpellMobAmbient = new Particle(ParticleType.SPELL_MOB_AMBIENT);
	public static final Particle SpellWitch = new Particle(ParticleType.SPELL_WITCH);
	public static final Particle DripWater = new Particle(ParticleType.DRIP_WATER);
	public static final Particle DripLava = new Particle(ParticleType.DRIP_LAVA);
	public static final Particle VillagerAngry = new Particle(ParticleType.VILLAGER_ANGRY);
	public static final Particle VillagerHappy = new Particle(ParticleType.VILLAGER_HAPPY);
	public static final Particle TownAura = new Particle(ParticleType.TOWN_AURA);
	public static final Particle Note = new Particle(ParticleType.NOTE);
	public static final Particle Portal = new Particle(ParticleType.PORTAL);
	public static final Particle EnchantmentTable = new Particle(ParticleType.ENCHANTMENT_TABLE);
	public static final Particle Flame = new Particle(ParticleType.FLAME);
	public static final Particle Lava = new Particle(ParticleType.LAVA);
	public static final Particle Footstep = new Particle(ParticleType.FOOTSTEP);
	public static final Particle Cloud = new Particle(ParticleType.CLOUD);
	public static final Particle Redstone = new Particle(ParticleType.REDSTONE);
	public static final Particle SnowBall = new Particle(ParticleType.SNOWBALL);
	public static final Particle SnowShovel = new Particle(ParticleType.SNOW_SHOVEL);
	public static final Particle Slime = new Particle(ParticleType.SLIME);
	public static final Particle Heart = new Particle(ParticleType.HEART);
	public static final Particle Barrier = new Particle(ParticleType.BARRIER);
	public static final Particle ItemTake = new Particle(ParticleType.ITEM_TAKE);
	public static final Particle MobAppearance = new Particle(ParticleType.MOB_APPEARANCE);
	
	public static Particle ItemCrack(ItemStack item) {
		if (item == null || item.getTypeId() == 0) return null;
		return Particle.ItemCrack(item.getTypeId(), item.getDurability());
	}
	
	public static Particle ItemCrack(int id) {
		return Particle.ItemCrack(id, 0);
	}
	
	public static Particle ItemCrack(int id, int data) {
		return new Particle(ParticleType.ITEM_CRACK, new int[] {id, data});
	}
	
	public static Particle BlockCrack(Block block) {
		if (block == null) return null;
		return Particle.BlockCrack(block.getTypeId());
	}
	
	public static Particle BlockCrack(int id) {
		if (id < 1) return null;
		return new Particle(ParticleType.BLOCK_CRACK, new int[] {id});
	}
	
	public static Particle BlockDust(Block block) {
		if (block == null) return null;
		return Particle.BlockDust(block.getTypeId());
	}
	
	public static Particle BlockDust(int id) {
		if (id < 1) return null;
		return new Particle(ParticleType.BLOCK_DUST, new int[] {id});
	}
	
	private ParticleType type;
	private int[] args;
	
	private boolean force;
	
	private Particle(ParticleType type) {
		this(type, new int[] {});
	}
	
	private Particle(ParticleType type, int... args) {
		this.type = type;
		this.args = args;
	}
	
	public ParticleType getType() {
		return this.type;
	}
	
	public int[] getArgs() {
		return this.args;
	}
	
	public Particle toForce() {
		Particle particle = this.copy();
		particle.force = true;
		return particle;
	}
	
	public boolean isForce() {
		return this.force;
	}
	
	private Particle copy() {
		return new Particle(this.type, this.args);
	}
	
	public Object getEnumObject() {
		return this.type.getEnumObject();
	}
	
}
