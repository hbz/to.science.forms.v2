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
@Target({TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = ValidArticleAdditionalMaterialUrlsValidator.class)
public @interface ValidArticleAdditionalMaterialUrls {

	String message() default "Please fill in the field \"Additional Material\" with a valid URL!";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
