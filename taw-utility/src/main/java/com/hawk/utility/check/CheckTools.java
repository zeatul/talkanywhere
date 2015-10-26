package com.hawk.utility.check;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class CheckTools {

	public static void check(Object object) throws Exception {
		if (object == null)
			return;
		Class<?> clazz = object.getClass();
		Field[] fields = clazz.getDeclaredFields();

		for (Field field : fields) {
			String fieldName = field.getName();

			PropertyDescriptor pd = new PropertyDescriptor(fieldName, clazz);
			Method reader = pd.getReadMethod();

			Object value = reader.invoke(object, new Object[] {});
			if (value!=null && value instanceof String){
				String temp = value.toString().trim();
				if (temp.length() == 0){
					/*String 去空格后，为 null*/
					value = null;
				}else if (((String)value).length() != temp.length()){
					/*String 去空格后，赋值回原对象*/
					Method writer = pd.getWriteMethod();
					writer.invoke(object, new Object[]{temp});
				}
				
			}
			
			/**
			 * 校验空值
			 */
			AllowNull allowNull = field.getAnnotation(AllowNull.class);
			if (allowNull != null && !allowNull.allow() && value == null) {
				if (value == null)
					throw new Exception("can't pass null check");				
			}			
			if (value == null)
				continue;
			
			/**
			 * 校验最大长度
			 */
			MaxLength maxLength = field.getAnnotation(MaxLength.class);
			if (maxLength != null){
				String str = (String)value;
				if(str.length() > maxLength.max())
					throw new Exception("can't pass max length check");
			}
			
			/**
			 * 校验正则
			 */
			Regex regex = field.getAnnotation(Regex.class);
			if (regex != null){
				String pattern = regex.pattern();
				String str = (String)value;
				if (! str.matches(pattern))
					throw new Exception("can't pass regex check");
			}
					
		}

	}

}
