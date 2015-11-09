/**
 * 
 */
package com.hawk.utility.check;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author pzhang1
 *
 */

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckSize {
//	public boolean allowEmpty() default false;
}
