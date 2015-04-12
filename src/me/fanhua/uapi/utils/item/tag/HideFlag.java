package me.fanhua.uapi.utils.item.tag;

public enum HideFlag {
	
	HIDE_ENCHANTS(1), HIDE_ATTRIBUTES(2), HIDE_UNBREAKABLE(4), HIDE_DESTROYS(8), HIDE_PLACED_ON(16), HIDE_POTION_EFFECTS(32);
	
	private int code;
	
	private HideFlag(int code) {
		this.code = code;
	}
	
	public boolean hasFlag(int flag) {
		return (flag & this.code) == this.code;
	}
	
	public int getCode() {
		return this.code;
	}
	
}
