package me.fanhua.uapi.gui_old.ui;

import java.util.HashMap;
import java.util.Map;

import com.google.common.io.ByteArrayDataOutput;

import me.fanhua.uapi.gui_old.ui.location.UILocation;
import me.fanhua.uapi.gui_old.ui.location.UILocationCentered;

public class NetworkUI {
	
	public static void wirteLocation(ByteArrayDataOutput output, UILocation location) {
		if (location instanceof UILocationCentered) {
			output.writeBoolean(false);
			output.writeInt(location.getX());
			output.writeInt(location.getY());
			
			UILocationCentered centered = (UILocationCentered) location;
			output.writeInt(centered.getWidth());
			output.writeInt(centered.getHiehgt());
		} else {
			output.writeBoolean(true);
			output.writeInt(location.getX());
			output.writeInt(location.getY());
		}
	}
	
	private static int mapId = 0;
	private static final Map<String, Integer> map = new HashMap<String, Integer>();
	
	static {
		NetworkUI.add(UIFrame.class);
		NetworkUI.add(UIBox.class);
		NetworkUI.add(UILabel.class);
		NetworkUI.add(UIItem.class);
		NetworkUI.add(UIButton.class);
		NetworkUI.add(UITextField.class);
		NetworkUI.add(UIPasswordField.class);
	}
	
	private static void add(Class<? extends UI> clazz) {
		NetworkUI.map.put(clazz.getName(), NetworkUI.mapId++);
	}
	
	public static void wirte(ByteArrayDataOutput output, UI ui) throws Throwable {
		output.writeInt(NetworkUI.map.get(ui.getClass().getName()));
		output.writeInt(ui.getId());
		ui.wirte(output);
	}
	
}
