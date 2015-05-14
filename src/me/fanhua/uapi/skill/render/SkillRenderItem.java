package me.fanhua.uapi.skill.render;

import me.fanhua.uapi.skill.Skill;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

public final class SkillRenderItem extends SkillRender implements ISkillSelectRender {
	
	private int slot;
	
	private int oldCD;
	private int oldUse;
	
	private ItemStack item;
	
	private boolean noSelected;
	
	public SkillRenderItem(Skill skill, int slot, int id) {
		this(skill, slot, new ItemStack(id, 1));
	}
	
	public SkillRenderItem(Skill skill, int slot, int id, int data) {
		this(skill, slot, new ItemStack(id, 1, (short) data));
	}
	
	public SkillRenderItem(Skill skill, int slot, ItemStack item) {
		super(skill);
		
		this.slot = slot;
		this.item = item.clone();
	}
	
	public SkillRenderItem setNoSelected() {
		this.noSelected = true;
		return this;
	}
	
	public boolean isSelected() {
		if (this.noSelected) return true;
		return this.isSelectedItem();
	}
	
	public boolean isSelectedItem() {
		return this.getSkill().getUser().getHeldSlot() == this.slot;
	}
	
	public int getSlot() {
		return this.slot;
	}
	
	public void setSlot(int slot) {
		this.getSkill().getPlayer().getInventory().setItem(this.slot, null);
		this.slot = slot;
		this.renderSelf();
	}
	
	public void switchSlot(SkillRenderItem render) {
		int slot = render.slot;
		render.slot = this.slot;
		this.slot = slot;
		
		render.renderSelf();
		this.renderSelf();
	}
	
	public void setItem(int id) {
		this.setItem(new ItemStack(id, 1));
	}
	
	public void setItem(int id, int data) {
		this.setItem(new ItemStack(id, 1, (short) data));
	}
	
	public void setItem(ItemStack item) {
		this.item = item.clone();
		this.renderSelf();
	}
	
	public ItemStack getItem() {
		return this.item.clone();
	}
	
	public boolean isNeedRender(int cd, int use) {
		if (cd == this.oldCD && use == this.oldUse) return false;
		
		this.oldCD = cd;
		this.oldUse = use;
		
		return true;
	}
	
	@Override
	public void draw() {
		Skill skill = this.getSkill();
		int cd = skill.getCD();
		int use = skill.getUse();
		
		if (!this.isNeedRender(cd, use)) return;
		
		this.renderItem(cd, use);
	}
	
	private void renderSelf() {
		Skill skill = this.getSkill();
		this.renderItem(skill.getCD(), skill.getUse());
	}
	
	private void renderItem(int cd, int use) {
		ItemStack item = this.getItem();
		if (cd != 0) item.setAmount(cd);
		else {
			item.addUnsafeEnchantment(Enchantment.WATER_WORKER, 10);
			item.setAmount(use);
		}
		
		this.getSkill().getPlayer().getInventory().setItem(this.slot, item);
	}
	
	@Override
	public void remove() {
		this.getSkill().getPlayer().getInventory().setItem(this.slot, null);
	}
	
}
