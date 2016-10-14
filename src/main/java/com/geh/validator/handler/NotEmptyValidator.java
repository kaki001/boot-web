package com.caimao.jyt.ashare.validator.handler;


import com.caimao.jyt.ashare.validator.annotation.NotEmpty;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by HuoBi on 2016/10/11.
 */
public class NotEmptyValidator implements ConstraintValidator<NotEmpty, Object> {

    @Override
    public void initialize(NotEmpty annotation) {
    }

    @Override
    public boolean isValid(Object str, ConstraintValidatorContext constraintValidatorContext) {
        return str != null;
    }

}