package edu.yangao.hedgehog.anno;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * 熔断 标注 注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Hedgehog {

    @AliasFor("serviceName")
    String value() default "";

    /**
     * 请求服务名
     */
    String serviceName() default "";
}
