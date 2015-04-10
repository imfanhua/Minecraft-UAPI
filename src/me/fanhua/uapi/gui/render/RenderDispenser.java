package me.fanhua.uapi.gui.render;

import me.fanhua.uapi.gui.type.GuiTypeDispenser;

public class RenderDispenser extends RenderObject {
	
	public RenderDispenser(GuiTypeDispenser type) {
		super(type.getInventory());
		
		this.init();
	}
	
	@Override
	public int getMaxWidth() {
		return 3;
	}
	
	@Override
	public int getMaxHeight() {
		return 3;
	}
	
}
