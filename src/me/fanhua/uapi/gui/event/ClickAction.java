package me.fanhua.uapi.gui.event;

public interface ClickAction {
	
	public static final int UNKNOWN = -1;
	
	public static final int MOUSE = 0;
	public static final int DROP = 1;
	public static final int HOTBAR = 2;
	public static final int DOUBLE = 3;
	
	public int getType();

}
