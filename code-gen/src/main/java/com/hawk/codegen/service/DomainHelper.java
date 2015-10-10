package com.hawk.codegen.service;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;




import com.hawk.codegen.config.IConfig;
import com.hawk.codegen.meta.Column;
import com.hawk.codegen.meta.Table;

public class DomainHelper {
	
	private IConfig config;
	
	public DomainHelper(IConfig config){
		this.config = config;
	}
	
	public Map<String,Object> transfer(Table table){
		String className = "";
		String packageName = "";
		String tableName = table.getName();
		String tableSchema = table.getSchema();
		String tableComment = table.getComment();
		int columnsLength = table.getColumns().size();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("tableName", tableName);
		map.put("tableSchema", tableSchema);
		map.put("tableComment", tableComment);
		/**
		 * 计算package 和 class name
		 */
		String[] strArray = tableName.split("_");
		packageName = strArray[1].toLowerCase();
		for (int i=2; i<strArray.length; i++){
			String str = strArray[i].toLowerCase();
			str =str.replaceFirst(str.substring(0, 1), str.substring(0, 1).toUpperCase())  ;
			className = className + str;
		}
		
		map.put("moduleName", packageName);
		map.put("packageName", config.getRootPackage() + "."+packageName);
		map.put("className", className);
		map.put("columnsLength", columnsLength);
		
		List<Map<String,Object>> fieldList = new ArrayList<Map<String,Object>>();
		map.put("fields", fieldList);
		boolean hasDate = false;
		
		for (Column column : table.getColumns()){
			Map<String,Object> fieldMap = filedToMap(column);
			fieldList.add(fieldMap);
			if ("Date".equals(fieldMap.get("filedType")))
				hasDate = true;
		}
		
		
		List<Map<String,Object>> keyFieldList = new ArrayList<Map<String,Object>>();
		map.put("key", keyFieldList);
		for (Column column : table.getPrimaryKey().getColumns()){
			Map<String,Object> fieldMap = filedToMap(column);
			keyFieldList.add(fieldMap);
		}
		
		if (hasDate){
			map.put("hasDate", hasDate);
		}
		
		return map;
	}
	
	private Map<String,Object> filedToMap(Column column){
		Map<String,Object> fieldMap = new HashMap<String,Object>();
		
		String columnName = column.getName();
		String columnType = column.getType();
		String columnComment = column.getComment();
		String fieldName = "";
		String filedType = getFieldType(columnType);
		
		fieldMap.put("columnName", columnName);
		fieldMap.put("columnType", columnType);
		fieldMap.put("columnComment", columnComment);
		
		/*计算field name 和 field type*/
		String[] strArray = columnName.split("_");
		for (int i=0; i<strArray.length; i++){
			String str = strArray[i].toLowerCase();
			if (i>0)
				str = str.replaceFirst(str.substring(0, 1), str.substring(0, 1).toUpperCase())  ;
			fieldName = fieldName + str;
		}
		
		fieldMap.put("fieldName", fieldName);
		fieldMap.put("filedType", filedType);
		
		return fieldMap;
	}
	
	private static Map<String,String> typeMap = new HashMap<String,String>();
	static{
		typeMap.put("int","Integer");
		typeMap.put("int identity","Integer");
		typeMap.put("datetime","Date");
		typeMap.put("date","Date");
		typeMap.put("varchar","String");
		typeMap.put("char","String");
		typeMap.put("nvarchar","String");
		typeMap.put("decimal", "java.math.BigDecimal");
	}
	
	private String getFieldType(String columnType){
		columnType = columnType.toLowerCase();
		String fieldType = typeMap.get(columnType);
		if (fieldType == null){
			System.out.println("unknown column type = "+columnType);
			throw new RuntimeException("unknown column type");
		}
		return fieldType;
	}
	
	

}
