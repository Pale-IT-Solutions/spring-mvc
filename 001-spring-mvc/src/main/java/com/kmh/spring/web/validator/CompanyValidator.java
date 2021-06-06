package com.kmh.spring.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.kmh.spring.web.form.CompanyForm;

public class CompanyValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return CompanyForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors e) {
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "name", "require", "Name is required");
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "address", "require", "Address is required");
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "type", "require", "Type is required");
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "phone", "require", "Phone is required");
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "size", "require", "Size is required");
	}
}