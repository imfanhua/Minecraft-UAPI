package me.fanhua.uapi.gui.event;

public final class ClickActionUnknown implements ClickAction {
	
	public static final ClickActionUnknown UNKNOWN = new ClickActionUnknown();
	
	@Override
	public int getType() {
		return ClickAction.UNKNOWN;
	}
	
	private ClickActionUnknown() {}
	
}
