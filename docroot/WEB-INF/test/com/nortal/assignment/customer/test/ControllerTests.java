package com.nortal.assignment.customer.test;

import static org.junit.Assert.assertEquals;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.ui.Model;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import com.nortal.assignment.addcustomer.controller.AddCustomerController;
import com.nortal.assignment.customer.data.CustomerDAO;
import com.nortal.assignment.customer.model.Customer;
import com.nortal.assignment.customer.validator.CustomerValidator;
import com.nortal.assignment.viewcustomers.controller.ViewCustomersController;

@RunWith(MockitoJUnitRunner.class)
public class ControllerTests {

	@Mock
	private RenderRequest request;
	@Mock
	private RenderResponse response;
	@Mock
	private Model model;
	@Mock
	private ActionRequest actionRequest;
	@Mock
	private ActionResponse actionResponse;
	@Mock
	private CustomerDAO mockCustomerDAO;
	@InjectMocks
	private AddCustomerController addCustomerController;
	@InjectMocks
	private ViewCustomersController viewCustomersController;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void addCustomerDefaultViewTest() {
		String viewName = addCustomerController.handleRenderRequest(request,
				response, model);
		assertEquals("addCustomerDefaultRender", viewName);
	}

	@Test
	public void viewCustomersDefaultViewTest() {
		String viewName = viewCustomersController.handleRenderRequest(request,
				response, model);
		assertEquals("viewCustomersDefaultRender", viewName);
	}

	@Test
	public void addCustomerSuccessfulTest() {
		assertEquals("addCustomerDefaultRender",
				addCustomerController.addCustomerMethod(request, response,
						model, "first", "name", "1990-8-22", ""));
		Mockito.verify(model).addAttribute("success",
				"Customer successfully added!");
	}

	@Test
	public void addCustomerWrongDateFormatTest() {
		assertEquals("addCustomerDefaultRender",
				addCustomerController.addCustomerMethod(request, response,
						model, "first", "name", "date", ""));
		Mockito.verify(model).addAttribute("error", "Wrong date format!");
	}

	@Test
	public void addCustomerFirstNameNullTest() throws ParseException {
		String firstName = "";
		String lastName = "last";
		String birthDate = "1990-8-22";
		String IDcode = "";
		Date parsedBirthDate = new Date(new SimpleDateFormat("yyyy-MM-dd")
				.parse(birthDate).getTime());
		Customer customer = new Customer(firstName, lastName, parsedBirthDate,
				IDcode);
		CustomerValidator validator = new CustomerValidator();
		Errors errors = new BeanPropertyBindingResult(customer, "customer");
		validator.validate(customer, errors);

		assertEquals("addCustomerDefaultRender",
				addCustomerController.addCustomerMethod(request, response,
						model, firstName, lastName, birthDate, IDcode));
		Mockito.verify(model).addAttribute("errors", errors.getAllErrors());
	}

}
