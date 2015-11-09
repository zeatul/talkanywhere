package com.hawk.utility.check;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;
import com.hawk.utility.EnumTools;

public class CheckTools {

	public static void check(Object object) throws Exception {
		if (object == null)
			throw new Exception("The param object is null");
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
			CheckNull checkNull = field.getAnnotation(CheckNull.class);
			if (checkNull != null && !checkNull.allowNull() && value == null) {
				if (value == null)
					throw new Exception(fieldName+" can't pass null check");				
			}			
			if (value == null)
				continue;
			
			/**
			 * 校验最大长度
			 */
			CheckMaxLength maxLength = field.getAnnotation(CheckMaxLength.class);
			if (maxLength != null){
				String str = (String)value;
				if(str.length() > maxLength.max())
					throw new Exception(fieldName + " can't pass max length check");
			}
			
			/**
			 * 校验正则
			 */
			CheckRegex regex = field.getAnnotation(CheckRegex.class);
			if (regex != null){
				String pattern = regex.pattern();
				String str = (String)value;
				if (! str.matches(pattern))
					throw new Exception(fieldName +" can't pass regex check");
			}
			
			/**
			 * 校验枚举
			 */
			CheckEnum checkEnum = field.getAnnotation(CheckEnum.class);
			if (checkEnum != null){
				EnumTools.parse(value, checkEnum.parser());
				
			/**
			 * 校验集合非空
			 */
			CheckSize checkSize = field.getAnnotation(CheckSize.class);
			if (checkSize != null){
				Collection<?> c = (Collection<?>)value;
				if (c.size() == 0)
					throw new Exception(fieldName + " can't be empty!");
			}
		}
					
		}

	}

}
