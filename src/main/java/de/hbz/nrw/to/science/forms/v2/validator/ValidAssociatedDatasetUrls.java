package de.hbz.nrw.to.science.forms.v2.validator;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

/**
 * @author Hasan Adoud
 */
@Retention(RUNTIME)
@Target({ TYPE })
@Constraint(validatedBy = ValidAssociatedDatasetUrlsValidator.class)
public @interface ValidAssociatedDatasetUrls {

	String message();

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
