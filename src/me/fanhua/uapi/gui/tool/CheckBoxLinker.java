package me.fanhua.uapi.gui.tool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.fanhua.uapi.event.EventTag;
import me.fanhua.uapi.event.base.bus.UBus;
import me.fanhua.uapi.event.base.event.UEvent;
import me.fanhua.uapi.gui.ui.UICheckBox;

public class CheckBoxLinker {
	
	public final UBus Bus = new UBus();
	
	public class SelectedEvent implements UEvent {
		
		private int code;
		private int oldCode;
		
		private UICheckBox ui;
		private UICheckBox oldUI;
		
		public SelectedEvent(int code, int oldCode, UICheckBox ui, UICheckBox oldUI) {
			this.code = code;
			this.oldCode = oldCode;
			
			this.ui = ui;
			this.oldUI = oldUI;
		}
		
		public CheckBoxLinker getLinker() {
			return CheckBoxLinker.this;
		}
		
		public UICheckBox getNewUI() {
			return this.ui;
		}
		
		public UICheckBox getOldUI() {
			return this.oldUI;
		}
		
		public int getNewCode() {
			return this.code;
		}
		
		public int getOldCode() {
			return this.oldCode;
		}
		
	}
	
	private Map<Integer, UICheckBox> map;
	private Map<UICheckBox, Integer> codes;
	
	private int selected;
	
	public CheckBoxLinker() {
		this.map = new HashMap<Integer, UICheckBox>();
		this.codes = new HashMap<UICheckBox, Integer>();
		
		this.selected = -1;
	}
	
	public int getSelectedCode() {
		return this.selected;
	}
	
	public UICheckBox getSelectedUI() {
		if (this.selected == -1) return null;
		return this.map.get(this.selected);
	}
	
	public void setSelected(int code) {
		if (this.selected == code) return;
		
		this.selected = code;
		for (UICheckBox ui : this.codes.keySet()) ui.setSelected(this.codes.get(ui) == code);
	}
	
	public void setSelected(UICheckBox ui) {
		this.setSelected(this.getCode(ui));
	}
	
	public CheckBoxLinker add(int code, UICheckBox ui) {
		this.map.put(code, ui);
		this.codes.put(ui, code);
		
		ui.Bus.register(this);
		return this;
	}
	
	public void remove(int code) {
		UICheckBox ui = this.map.get(code);
		this.map.remove(code);
		this.codes.remove(ui);
		
		ui.Bus.unregister(this);
	}
	
	public UICheckBox getUI(int code) {
		return this.map.get(code);
	}
	
	public int getCode(UICheckBox ui) {
		Integer code = this.codes.get(ui);
		if (code == null) return -1;
		else return code;
	}
	
	public List<UICheckBox> getAllUI() {
		return new ArrayList<UICheckBox>(this.map.values());
	}
	
	public void clear() {
		for (UICheckBox ui : this.map.values()) ui.Bus.unregister(this);;
		this.map.clear();
		this.codes.clear();
	}
	
	public int size() {
		return this.map.size();
	}
	
	@EventTag
	@Deprecated
	public void onSelected(UICheckBox.SelectedEvent event) {
		UICheckBox ui = event.getUI();
		int code = this.getCode(ui);
		if (this.selected == code) return;
		
		int old = this.selected;
		this.setSelected(code);
		UBus.report(this.Bus.call(new SelectedEvent(code, old, ui, this.getUI(old))));
	}
	
}
