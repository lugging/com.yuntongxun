/**
 * 
 */
package com.yuntongxun.weixin.aspect;

import java.lang.annotation.*;

/**
 * @author zhailiang
 *
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ServiceLog {

}
