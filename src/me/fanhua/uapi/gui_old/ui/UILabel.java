package me.fanhua.uapi.gui_old.ui;

import com.google.common.io.ByteArrayDataOutput;

import me.fanhua.uapi.gui_old.Gui;
import me.fanhua.uapi.gui_old.ui.location.UILocation;
import me.fanhua.uapi.network.Network;
import me.fanhua.uapi.network.packet.NetPacketUIMoveLocation;
import me.fanhua.uapi.network.packet.NetPacketUISetCentered;
import me.fanhua.uapi.network.packet.NetPacketUISetText;
import me.fanhua.uapi.network.packet.NetPacketUISetVisible;

public class UILabel extends UI {
	
	private UILocation location;
	private String title;
	private boolean centered;
	
	private boolean visible;
	
	public UILabel(Gui screen, UILocation location, String title, boolean centered) {
		super(screen);
		
		this.location = location;
		this.title = title;
		this.centered = centered;
		
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
	
	public String getTitle() {
		return this.title;
	}
	
	public void setTitle(String title) {
		this.title = title;
		
		try {
			Network.send(this.getScreen().getUser(), new NetPacketUISetText(this, this.title));
		} catch (Throwable error) {}
	}
	
	public boolean isCentered() {
		return this.centered;
	}
	
	public void setCentered(boolean centered) {
		 this.centered = centered;
		 
		try {
			Network.send(this.getScreen().getUser(), new NetPacketUISetCentered(this, this.centered));
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
		output.writeUTF(this.title);
		output.writeBoolean(this.centered);
	}
	
}
