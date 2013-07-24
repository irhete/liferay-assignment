package com.nortal.assignment.customer.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.nortal.assignment.customer.model.Customer;

public class CustomerValidator implements Validator {

	@Override
	public boolean supports(Class clazz) {
		return Customer.class.equals(clazz);
	}

	@Override
	public void validate(Object customer, Errors e) {
		ValidationUtils.rejectIfEmpty(e, "firstName", "empty",
				"First name can not be empty");
		ValidationUtils
				.rejectIfEmpty(e, "lastName", "empty", "Last name empty");
		ValidationUtils.rejectIfEmpty(e, "birthDate", "empty",
				"Birth date can not be empty");
	}

}
