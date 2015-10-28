package com.hawk.pub.enums;

public enum EnumBoolean {
	
	FALSE((short)0),TRUE((short)1);
	
	private short value;
	
	
	
	private EnumBoolean(short value){
		this.value = value;
	}

	
	@Override
	public String toString() {		
		return String.valueOf(value);		
	}
	
	public short getValue() {
		return value;
	}
}
