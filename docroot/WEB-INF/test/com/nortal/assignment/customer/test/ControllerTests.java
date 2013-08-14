package com.nortal.assignment.customer.test;

import static org.junit.Assert.assertEquals;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
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
	public void addCustomerSuccessfulTest() throws ParseException {
		Customer customer = new Customer("first", "name", getValidDate(), "");
		assertEquals("addCustomerDefaultRender",
				addCustomerController.addCustomerMethod(customer, result,
						request, response, model));
		Mockito.verify(model).addAttribute("success",
				"Customer successfully added!");
	}

	@Test
	public void addCustomerFirstNameNullTest() throws ParseException {
		String firstName = "";
		String lastName = "last";
		Date birthDate = getValidDate();
		String IDcode = "";
		Customer customer = new Customer(firstName, lastName, birthDate, IDcode);
		Mockito.when(result.hasErrors()).thenReturn(true);
		assertEquals("addCustomerDefaultRender",
				addCustomerController.addCustomerMethod(customer, result,
						request, response, model));
		Mockito.verify(model, Mockito.never()).addAttribute("success",
				"Customer successfully added!");
	}

	private Date getValidDate() throws ParseException {
		Date parsedBirthDate = new Date(new SimpleDateFormat("yyyy-MM-dd")
				.parse("1990-8-22").getTime());
		return parsedBirthDate;
	}
}
