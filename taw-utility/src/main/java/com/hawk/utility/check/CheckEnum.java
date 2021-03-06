package com.hawk.utility.check;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckEnum {
	public Class<? extends Enum<?>> parser();
}
