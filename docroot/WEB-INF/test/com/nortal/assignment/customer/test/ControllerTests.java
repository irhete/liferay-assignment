package com.nortal.assignment.customer.test;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.nortal.assignment.addcustomer.controller.AddCustomerController;
import com.nortal.assignment.customer.model.Customer;
import com.nortal.assignment.customer.service.CustomerService;
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
	private CustomerService mockCustomerService;
	@Mock
	private BindingResult result;
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
	public void viewCustomersDefaultViewTest() throws SystemException,
			PortalException {
		String viewName = viewCustomersController.handleRenderRequest(request,
				response, model);
		assertEquals("viewCustomersDefaultRender", viewName);
	}

	@Test
	public void addCustomerSuccessfulTest() {
		Customer customer = new Customer("first", "name", "1990-8-22", "");
		assertEquals("addCustomerDefaultRender",
				addCustomerController.addCustomerMethod(request, response,
						model, customer, result));
		Mockito.verify(model).addAttribute("success",
				"Customer successfully added!");
	}

	@Ignore
	@Test
	public void addCustomerWrongDateFormatTest() {
		Customer customer = new Customer("first", "name", "date", "");
		assertEquals("addCustomerDefaultRender",
				addCustomerController.addCustomerMethod(request, response,
						model, customer, result));
		Mockito.verify(model).addAttribute("error", "Wrong date format!");
	}

	@Test
	public void addCustomerFirstNameNullTest() throws ParseException {
		String firstName = "";
		String lastName = "last";
		String birthDate = "1990-8-22";
		String IDcode = "";
		Customer customer = new Customer(firstName, lastName, birthDate, IDcode);
		Mockito.when(result.hasErrors()).thenReturn(true);
		assertEquals("addCustomerDefaultRender",
				addCustomerController.addCustomerMethod(request, response,
						model, customer, result));
		Mockito.verify(model, Mockito.never()).addAttribute("success",
				"Customer successfully added!");
	}
}
