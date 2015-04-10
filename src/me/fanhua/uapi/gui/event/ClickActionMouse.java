package me.fanhua.uapi.gui.event;

public class ClickActionMouse implements ClickAction {
	
	public static final int LEFT = 0;
	public static final int RIGHT = 1;
	public static final int MIDDLE = 2;
	
	@Override
	public int getType() {
		return ClickAction.MOUSE;
	}
	
	private int key;
	private boolean shift;
	
	public ClickActionMouse(int key, boolean shift) {
		this.key = key;
		this.shift = shift;
	}
	
	public int getKey() {
		return this.key;
	}
	
	public boolean isShiftPressed() {
		return this.shift;
	}
	
	public boolean isKeyLeftClicked() {
		return this.key == ClickActionMouse.LEFT;
	}
	
	public boolean isKeyRightClicked() {
		return this.key == ClickActionMouse.RIGHT;
	}
	
	public boolean isKeyMiddleClicked() {
		return this.key == ClickActionMouse.MIDDLE;
	}
	
}
