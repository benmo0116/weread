package com.wxy.annotation;

/**
 * @author wxy
 * @create 2018-02-06 15:44
 * @desc 自定义校验注解  字母全小写？
 **/

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({METHOD, FIELD, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = NicknameValidator.class)
@Documented
public @interface NicknameValidate {
    //    String value();
    CaseMode value();

    //    String message() default "{昵称不能含大写字母}";
    String message() default "{昵称不合乎规范}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
