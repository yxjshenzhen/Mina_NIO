package com.you.mina.bean;

public class EquipmentBean {

	private String Type;
	private String Name;
	private String Value;
	
	public EquipmentBean() {
		super();
	}
	public EquipmentBean(String type, String name, String value) {
		super();
		Type = type;
		Name = name;
		Value = value;
	}
	public String getType() {
		return Type;
	}
	public void setType(String type) {
		Type = type;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getValue() {
		return Value;
	}
	public void setValue(String value) {
		Value = value;
	}

}
