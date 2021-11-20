package com.stsoft.socksaccounting.validators;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.ElementType;

import javax.validation.Constraint;
import javax.validation.Payload;

/*
 * Аннотация RequestOperationsConstraint
 * существует для валидации параметра operation
 * в запросе числа носок на складе 
 */
@Documented
@Constraint(validatedBy = RequestOperationsValidator.class)
@Target({ ElementType.PARAMETER, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestOperationsConstraint {
    String message() default "Invalid operation param";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
