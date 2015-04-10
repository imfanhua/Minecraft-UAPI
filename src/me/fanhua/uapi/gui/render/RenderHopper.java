package me.fanhua.uapi.gui.render;

import me.fanhua.uapi.gui.type.GuiTypeHopper;

public class RenderHopper extends RenderObject {
	
	public RenderHopper(GuiTypeHopper type) {
		super(type.getInventory());
		
		this.init();
	}
	
	@Override
	public int getMaxWidth() {
		return 5;
	}
	
	@Override
	public int getMaxHeight() {
		return 1;
	}
	
}
