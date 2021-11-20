package com.stsoft.socksaccounting.validators;

import java.util.Arrays;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/*
 * Класс RequestOperationsValidator
 * производит валидацию параметра operation
 * в запросе числа носок на складе 
 */
public class RequestOperationsValidator implements ConstraintValidator<RequestOperationsConstraint, String> {
    private final List<String> grantedOperations = Arrays.asList("morethan", "lessthan", "equal");

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (grantedOperations.contains(value.toLowerCase())) {
            return true;
        }
        return false;
    }
}
