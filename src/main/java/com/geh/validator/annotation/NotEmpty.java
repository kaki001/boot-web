package com.caimao.jyt.ashare.validator.annotation;

import com.caimao.jyt.ashare.validator.handler.NotEmptyValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;



/**
 * Created by HuoBi on 2016/10/11.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER })
@Constraint(validatedBy = { NotEmptyValidator.class })
public @interface NotEmpty {

    String field() default "";

    String message() default "{validator.not.empty}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}