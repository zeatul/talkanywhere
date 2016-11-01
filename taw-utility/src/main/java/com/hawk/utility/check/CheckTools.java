package com.hawk.utility.check;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hawk.exception.BasicException;
import com.hawk.utility.EnumTools;

public class CheckTools {

	private final static Logger logger = LoggerFactory.getLogger(CheckTools.class);

	public static void check(Object object) throws Exception {
		if (object == null)
			throw new BasicException("The param object is null");
		Class<?> clazz = object.getClass();
		Field[] fields = clazz.getDeclaredFields();

		for (Field field : fields) {
			String fieldName = field.getName();

			PropertyDescriptor pd = new PropertyDescriptor(fieldName, clazz);
			Method reader = pd.getReadMethod();

			Object value = reader.invoke(object, new Object[] {});
			if (value != null && value instanceof String) {
				String temp = value.toString().trim();
				if (temp.length() == 0) {
					/* String 去空格后，为 null */
					value = null;
				} else if (((String) value).length() != temp.length()) {
					/* String 去空格后，赋值回原对象 */
					Method writer = pd.getWriteMethod();
					writer.invoke(object, new Object[] { temp });
				}

			}

			/**
			 * 校验空值
			 */
			CheckNull checkNull = field.getAnnotation(CheckNull.class);
			if (checkNull != null && value == null) {
				throw new BasicException(fieldName + " can't pass null check");
			}
			if (value == null)
				continue;

			/**
			 * 校验最大长度
			 */
			CheckMaxLength maxLength = field.getAnnotation(CheckMaxLength.class);
			if (maxLength != null) {
				String str = (String) value;
				if (str.length() > maxLength.max())
					throw new BasicException(fieldName + " can't pass max length check");
			}

			/**
			 * 校验正则
			 */
			CheckRegex regex = field.getAnnotation(CheckRegex.class);
			if (regex != null) {
				String pattern = regex.pattern();
				String str = (String) value;
				if (!str.matches(pattern))
					throw new BasicException(fieldName + " can't pass regex check");
			}

			/**
			 * 校验枚举
			 */
			CheckEnum checkEnum = field.getAnnotation(CheckEnum.class);
			if (checkEnum != null) {
				try {
					EnumTools.parse(value, checkEnum.parser());
				} catch (Exception e) {
					logger.error("enum parse error", e);
					throw new BasicException(fieldName + " can't pass enum check!,cause="+e.getMessage());
				}
			}

			/**
			 * 校验集合非空
			 */
			CheckSize checkSize = field.getAnnotation(CheckSize.class);
			if (checkSize != null) {
				Collection<?> c = (Collection<?>) value;
				if (c.size() == 0)
					throw new BasicException(fieldName + " can't be empty!");
			}
		}

	}

}
