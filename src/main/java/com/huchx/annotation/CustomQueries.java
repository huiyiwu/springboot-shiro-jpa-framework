package com.huchx.annotation;

import java.lang.annotation.*;

/**
 * 暂时无用，后续使用此注解进行自定义sql，类似于@NamedQueries
 */
@Inherited
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface CustomQueries {
    CustomQuery[] queries();
}
