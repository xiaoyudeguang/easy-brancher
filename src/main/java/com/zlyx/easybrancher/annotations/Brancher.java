package com.zlyx.easybrancher.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

/**
 * @Auth 赵光
 * @Describle 生命分支容器
 * @2018年12月27日 下午8:33:06
 */
@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Component
public @interface Brancher {
	
	@AliasFor(annotation = Component.class, attribute = "value")
	String value() default "";

   /**
     *   开关
    * @return
    */
   boolean isOpen() default true;
   
   /**
     *  唯一标识
    * @return
    */
   String key();
}
