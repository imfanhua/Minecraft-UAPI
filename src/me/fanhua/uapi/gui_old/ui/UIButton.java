package me.fanhua.uapi.gui_old.ui;

import com.google.common.io.ByteArrayDataOutput;

import me.fanhua.uapi.event.self.base.Event;
import me.fanhua.uapi.event.self.ui.UIButtonClickEvent;
import me.fanhua.uapi.gui_old.Gui;
import me.fanhua.uapi.gui_old.ui.location.UILocation;
import me.fanhua.uapi.network.Network;
import me.fanhua.uapi.network.packet.NetPacketUIMoveLocation;
import me.fanhua.uapi.network.packet.NetPacketUIMoveSize;
import me.fanhua.uapi.network.packet.NetPacketUISetEnabled;
import me.fanhua.uapi.network.packet.NetPacketUISetText;
import me.fanhua.uapi.network.packet.NetPacketUISetVisible;

public class UIButton extends UI {
	
	public Event<UIButtonClickEvent> onClick = new Event<UIButtonClickEvent>();
	
	private UILocation location;
	private int width;
	private int height;
	private String title;
	
	private boolean enabled;
	private boolean visible;
	
	public UIButton(Gui screen, UILocation location, int width, int height, String title) {
		super(screen);
		
		this.location = location;
		this.width = width;
		this.height = height;
		this.title = title;
		
		this.enabled = true;
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

	public String getTitle() {
		return this.title;
	}
	
	public void setTitle(String title) {
		this.title = title;
		
		try {
			Network.send(this.getScreen().getUser(), new NetPacketUISetText(this, this.title));
		} catch (Throwable error) {}
	}
	
	public boolean isEnabled() {
		return this.enabled;
	}
	
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
		
		try {
			Network.send(this.getScreen().getUser(), new NetPacketUISetEnabled(this, this.enabled));
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
	
	public void onClick() {
		this.onClick.call(new UIButtonClickEvent(this));
	}
	
	@Override
	public void wirte(ByteArrayDataOutput output) {
		NetworkUI.wirteLocation(output, this.location);
		output.writeInt(this.width);
		output.writeInt(this.height);
		
		output.writeUTF(this.title);
	}
	
}
