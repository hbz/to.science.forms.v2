package de.hbz.nrw.to.science.forms.v2.validator;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Retention(RUNTIME)
@Target({ FIELD, TYPE, TYPE_USE })
@Constraint(validatedBy = AtLeastOneValidator.class)
public @interface AtLeastOne {
	
	String value();
	String message();
	
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
