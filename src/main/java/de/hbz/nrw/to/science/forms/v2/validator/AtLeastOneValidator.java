package de.hbz.nrw.to.science.forms.v2.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import de.hbz.nrw.to.science.forms.v2.model.parent.SimpleObject;
import lombok.Getter;
import lombok.Setter;

@Component
@Getter @Setter
public class AtLeastOneValidator implements ConstraintValidator<AtLeastOne, Object>{
	
	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		
		boolean isValid = false;
		
		if(value instanceof SimpleObject) {
			SimpleObject pref = (SimpleObject) value;
			isValid = !StringUtils.isBlank(pref.getId());
		} 
		
		if(value instanceof String) {
			String str = (String) value;
			isValid = !StringUtils.isBlank(str);
		}
		
		if (!isValid) {
	        context.disableDefaultConstraintViolation();  // to have different messages depending on the different issue
	        context
	        	.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
	        	.addConstraintViolation();
	        
	    }

		return isValid;
		
	}

}
