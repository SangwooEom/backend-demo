package com.sangwoo.backenddemo.type;

public enum YNType {
	Y("Y"),N("N");
	
	private String value;
	
	private YNType(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
}
