package com.nortal.assignment.customer.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Before;
import org.junit.Test;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import com.nortal.assignment.customer.model.Customer;
import com.nortal.assignment.customer.validator.CustomerValidator;

public class ValidatorTests {

	private CustomerValidator validator;
	private Errors errors;
	private Customer customer;

	@Before
	public void setUp() {
		customer = new Customer();
		validator = new CustomerValidator();
		errors = new BeanPropertyBindingResult(customer, "customer");
	}

	@Test
	public void validateSuccessfulTest() throws ParseException {
		customer.setFirstName("new");
		customer.setLastName("customer");
		customer.setBirthDate("1990-8-22");

		validator.validate(customer, errors);
		assertEquals(false, errors.hasErrors());
	}

	@Test
	public void validateFirstNameNullTest() throws ParseException {
		customer.setLastName("customer");
		customer.setBirthDate("1990-8-22");

		validator.validate(customer, errors);
		assertNotNull(errors.getFieldError("firstName"));
	}

	@Test
	public void validateLastNameNullTest() throws ParseException {
		customer.setFirstName("new");
		customer.setBirthDate("1990-8-22");

		validator.validate(customer, errors);
		assertNotNull(errors.getFieldError("lastName"));
	}

	@Test
	public void validateBirthDateNullTest() {
		customer.setFirstName("new");
		customer.setLastName("customer");

		validator.validate(customer, errors);
		assertNotNull(errors.getFieldError("birthDate"));
	}

	private Date getValidDate() throws ParseException {
		Date parsedBirthDate = new Date(new SimpleDateFormat("yyyy-MM-dd")
				.parse("1990-8-22").getTime());
		return parsedBirthDate;
	}
}
