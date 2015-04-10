package me.fanhua.uapi.gui.event;

public class ClickActionDrop implements ClickAction {
	
	@Override
	public int getType() {
		return ClickAction.DROP;
	}
	
	private boolean control;
	
	public ClickActionDrop(boolean control) {
		this.control = control;
	}
	
	public boolean isControlPressed() {
		return this.control;
	}
	
}
