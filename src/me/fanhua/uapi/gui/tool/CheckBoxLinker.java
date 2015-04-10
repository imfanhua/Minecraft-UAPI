package me.fanhua.uapi.gui.tool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.fanhua.uapi.event.base.UEvent;
import me.fanhua.uapi.event.base.UListener;
import me.fanhua.uapi.gui.event.tool.CheckBoxSelectedEvent;
import me.fanhua.uapi.gui.event.ui.UICheckBoxSelectedEvent;
import me.fanhua.uapi.gui.ui.UICheckBox;

public class CheckBoxLinker implements UListener<UICheckBoxSelectedEvent> {
	
	public final UEvent<CheckBoxSelectedEvent> EventSelected = new UEvent<CheckBoxSelectedEvent>();
	
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
		
		ui.EventSelected.add(this);
		return this;
	}
	
	public void remove(int code) {
		UICheckBox ui = this.map.get(code);
		this.map.remove(code);
		this.codes.remove(ui);
		
		ui.EventSelected.remove(this);
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
		for (UICheckBox ui : this.map.values()) ui.EventSelected.remove(this);
		this.map.clear();
		this.codes.clear();
	}
	
	public int size() {
		return this.map.size();
	}

	@Override
	public void call(UICheckBoxSelectedEvent event) {
		UICheckBox ui = event.getUI();
		ui.setSelected(true);
		int code = this.getCode(ui);
		if (this.selected == code) return;
		
		this.setSelected(code);
		this.EventSelected.call(new CheckBoxSelectedEvent(this, code, ui));
	}
	
}
