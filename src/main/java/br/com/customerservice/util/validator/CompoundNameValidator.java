package br.com.customerservice.util.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CompoundNameValidator implements ConstraintValidator<CompoundName, String>{

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if(value == null || value.isBlank())
			return true;
		
		return value.split(" ").length > 1;
	}

}
