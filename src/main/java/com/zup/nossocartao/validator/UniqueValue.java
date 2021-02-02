package com.zup.nossocartao.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {UniqueValueValidator.class})
public @interface UniqueValue {

    String message() default "{com.zup.beanvalidation.uniquevalue}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    String fieldName();

    Class<?> domainClass();

}
