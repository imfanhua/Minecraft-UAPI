package me.fanhua.uapi.gui.event;

public class ClickActionHotbar implements ClickAction {
	
	@Override
	public int getType() {
		return ClickAction.HOTBAR;
	}
	
	private int solt;
	
	public ClickActionHotbar(int solt) {
		this.solt = solt;
	}
	
	public int getSolt() {
		return this.solt;
	}
	
}
