package com.hawk.utility;


public class EnumTools {
	
	/**
	 * 将obj 转换成对应的枚举
	 * @param obj
	 * @param clazz
	 * @return
	 */
	public static <T extends Enum<?>> T parse(Object obj,Class<T> clazz){
		
		if(obj == null)
			return null;
		
		if (StringTools.isNullOrEmpty(obj.toString()))
			return null;
		
		 T[] array = clazz.getEnumConstants();
		 
		 for(T t : array){
			 if (t.toString().equals(obj.toString()))
				 return t;			 
		 }
		 
		 throw new RuntimeException("parse error:no match enum value,obj="+obj+",clazz="+clazz.getName());
		
	}

}
