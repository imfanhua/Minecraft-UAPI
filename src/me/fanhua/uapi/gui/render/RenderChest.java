package me.fanhua.uapi.gui.render;

import me.fanhua.uapi.gui.type.GuiTypeChest;

public class RenderChest extends RenderObject {
	
	private int height;
	
	public RenderChest(GuiTypeChest type) {
		super(type.getInventory());
		this.height = type.getHeight();
		
		this.init();
	}
	
	@Override
	public int getMaxWidth() {
		return 9;
	}
	
	@Override
	public int getMaxHeight() {
		return this.height;
	}
	
}
