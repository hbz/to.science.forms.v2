package de.hbz.nrw.to.science.forms.v2.validator;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Retention(RUNTIME)
@Target({TYPE})
@Constraint(validatedBy = NotBothFieldsEmptyValidator.class)
public @interface NotBothFieldsEmpty {

	String first();
	String second();
	String field1();
	String field2();
	String message();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
 
}
