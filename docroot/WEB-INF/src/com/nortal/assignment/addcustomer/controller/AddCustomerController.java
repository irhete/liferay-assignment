package com.nortal.assignment.addcustomer.controller;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.nortal.assignment.customer.model.Customer;
import com.nortal.assignment.customer.service.CustomerService;
import com.nortal.assignment.customer.validator.CustomerValidator;

@Controller(value = "AddCustomerController")
@RequestMapping("VIEW")
public class AddCustomerController {
	@Resource
	private CustomerService customerService;

	@InitBinder
	public void allowEmptyDateBinding(WebDataBinder binder) {
		// Allow for null values in date fields.
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				new SimpleDateFormat("yyyy-MM-dd"), true));
		// tell spring to set empty values as null instead of empty string.
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}

	@RenderMapping
	public String handleRenderRequest(RenderRequest request,
			RenderResponse response, Model model) {
		return "addCustomerDefaultRender";
	}

	@RenderMapping(params = "action=addCustomer")
	public String addCustomerMethod(
			@ModelAttribute("customer") Customer customer,
			BindingResult result, RenderRequest request,
			RenderResponse response, Model model) {
		try {
			CustomerValidator validator = new CustomerValidator();
			validator.validate(customer, result);
			if (!result.hasErrors()) {
				customerService.addCustomer(customer);
				model.addAttribute("success", "Customer successfully added!");
			}

		} catch (SystemException e) {
			result.reject("customer", "Customer could not be saved");
		} catch (PortalException e) {
			result.reject("customer", "Customer could not be saved");
		} catch (SQLException e) {
			result.reject("customer", "Customer could not be saved");
		}

		return "addCustomerDefaultRender";
	}
}
