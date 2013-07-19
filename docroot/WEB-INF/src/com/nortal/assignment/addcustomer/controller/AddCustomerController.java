package com.nortal.assignment.addcustomer.controller;

import java.sql.SQLException;

import javax.annotation.Resource;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.nortal.assignment.customer.messagesource.VerticalDatabaseMessageSource;
import com.nortal.assignment.customer.model.Customer;
import com.nortal.assignment.customer.service.CustomerService;
import com.nortal.assignment.customer.validator.CustomerValidator;

@Controller(value = "AddCustomerController")
@RequestMapping("VIEW")
public class AddCustomerController {
	@Resource
	private VerticalDatabaseMessageSource messageSource;
	@Resource
	private CustomerService customerService;

	@RenderMapping
	public String handleRenderRequest(RenderRequest request,
			RenderResponse response, Model model) {
		return "addCustomerDefaultRender";
	}

	@RenderMapping(params = "action=addCustomer")
	public String addCustomerMethod(RenderRequest request,
			RenderResponse response, Model model,
			@ModelAttribute(value = "customer") Customer customer,
			BindingResult result) {
		try {
			CustomerValidator validator = new CustomerValidator();
			validator.validate(customer, result);

			if (!result.hasErrors()) {
				customerService.addCustomer(customer);
				model.addAttribute("success", "Customer successfully added!");
			}
		} catch (SystemException e) {
			result.rejectValue("customer", "error");
		} catch (PortalException e) {
			result.rejectValue("customer", "error");
		} catch (SQLException e) {
			result.rejectValue("customer", "error");
		}

		return "addCustomerDefaultRender";
	}
}
