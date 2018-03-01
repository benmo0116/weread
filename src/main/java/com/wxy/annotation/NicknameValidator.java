package com.wxy.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author wxy
 * @create 2018-02-06 15:49
 * @desc ${DESCRIPTION}
 **/
public class NicknameValidator implements ConstraintValidator<NicknameValidate, String> {

    private CaseMode caseMode;

    @Override
    public void initialize(NicknameValidate constraintAnnotation) {
        this.caseMode = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null)
            return true;

        if (caseMode == CaseMode.UPPER)
            return value.equals(value.toUpperCase());
        else
            return value.equals(value.toLowerCase());
    }
}
