package com.pnu.db.Graduates.Application.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UniqueDegreeValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueDegreeConstraint {
    String message() default "Degree should be unique";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
