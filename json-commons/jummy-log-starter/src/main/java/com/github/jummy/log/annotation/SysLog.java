package com.github.jummy.log.annotation;

import java.lang.annotation.*;

/**
 * @author: jummy
 * @create_date: 14/09/2021 4:58 下午
 * @desc:
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLog {

    /**
     * 描述
     *
     * @return {string}
     */
    String value();

    /**
     * 记录执行参数
     *
     * @return
     */
    boolean recordRequestParam() default true;

    /**
     * 记录返回参数
     *
     * @return
     */
    boolean recordResponseParam() default true;

}
