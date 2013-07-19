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
	public void validate(Object arg0, Errors e) {
		ValidationUtils.rejectIfEmpty(e, "firstName", "empty");
		ValidationUtils.rejectIfEmpty(e, "lastName", "empty");
		ValidationUtils.rejectIfEmpty(e, "birthDate", "empty");
	}

}
