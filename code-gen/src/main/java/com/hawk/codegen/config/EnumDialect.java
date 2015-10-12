package com.hawk.codegen.config;

public enum EnumDialect {
	
	Mysql("mysql");
	
	private String name;
	
	
	private EnumDialect(String name){
		this.name = name;
	}
	
	@Override
	public String toString() {
		return this.name;
	}

}
