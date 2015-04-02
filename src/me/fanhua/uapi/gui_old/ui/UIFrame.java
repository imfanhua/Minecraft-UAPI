package me.fanhua.uapi.gui_old.ui;

import com.google.common.io.ByteArrayDataOutput;

import me.fanhua.uapi.gui_old.Gui;
import me.fanhua.uapi.gui_old.ui.location.UILocation;
import me.fanhua.uapi.network.Network;
import me.fanhua.uapi.network.packet.NetPacketUIMoveLocation;
import me.fanhua.uapi.network.packet.NetPacketUIMoveSize;
import me.fanhua.uapi.network.packet.NetPacketUISetVisible;

public class UIFrame extends UI {
	
	private UILocation location;
	private int width;
	private int height;
	
	private boolean visible;
	
	public UIFrame(Gui screen, UILocation location, int width, int height) {
		super(screen);
		
		this.location = location;
		this.width = width;
		this.height = height;
		
		this.visible = true;
	}
	
	public UILocation getLocation() {
		return this.location;
	}
	
	public void setLocation(UILocation location) {
		this.location = location;
		
		try {
			Network.send(this.getScreen().getUser(), new NetPacketUIMoveLocation(this, this.location));
		} catch (Throwable error) {}
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public int getHeight() {
		return this.height;
	}
	
	public void setSize(int width, int height) {
		this.width = width;
		this.height = height;
		
		try {
			Network.send(this.getScreen().getUser(), new NetPacketUIMoveSize(this, this.width, this.height));
		} catch (Throwable error) {}
	}
	
	public boolean isVisible() {
		return this.visible;
	}
	
	public void setVisible(boolean visible) {
		this.visible = visible;
		
		try {
			Network.send(this.getScreen().getUser(), new NetPacketUISetVisible(this, this.visible));
		} catch (Throwable error) {}
	}
	
	@Override
	public void wirte(ByteArrayDataOutput output) {
		NetworkUI.wirteLocation(output, this.location);
		output.writeInt(this.width);
		output.writeInt(this.height);
	}
	
}
