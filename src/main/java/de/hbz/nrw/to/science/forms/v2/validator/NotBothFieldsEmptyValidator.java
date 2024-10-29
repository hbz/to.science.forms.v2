package de.hbz.nrw.to.science.forms.v2.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@Getter @Setter
public class NotBothFieldsEmptyValidator implements ConstraintValidator<NotBothFieldsEmpty, Object>{

	String firstFieldName;
	String secondFieldName;
	
	@Override
	  public void initialize(final NotBothFieldsEmpty constraintAnnotation) {
	    this.firstFieldName = constraintAnnotation.first();
	    this.secondFieldName = constraintAnnotation.second();
	  }
	
	
	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		
		String firstObj = (String) new BeanWrapperImpl(value).getPropertyValue(firstFieldName);
		String secondObj = (String) new BeanWrapperImpl(value).getPropertyValue(secondFieldName);
		
		if(firstObj != null) {
			boolean isValid = !StringUtils.isBlank(firstObj) || !StringUtils.isBlank(secondObj);

			if (!isValid) {
		        context.disableDefaultConstraintViolation();  // to have different messages depending on the different issue
		        context
		        	.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
		        	.addPropertyNode("creator[0]")
		        	.addConstraintViolation();
		    }
			
			return isValid;
		}
		return true;
	}

}
