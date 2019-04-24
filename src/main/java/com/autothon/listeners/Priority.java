package com.autothon.listeners;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;

	/**
	 * The method creates priority annotation.
	 */
	@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
	@Target({ElementType.METHOD, ElementType.TYPE})
	public @interface Priority {
		int value() default 0;
	}
