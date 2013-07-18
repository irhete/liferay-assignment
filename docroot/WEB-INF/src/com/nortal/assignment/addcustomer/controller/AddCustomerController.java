package com.nortal.assignment.addcustomer.controller;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.annotation.Resource;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.nortal.assignment.customer.data.CustomerDAO;
import com.nortal.assignment.customer.model.Customer;
import com.nortal.assignment.customer.validator.CustomerValidator;

@Controller(value = "AddCustomerController")
@RequestMapping("VIEW")
public class AddCustomerController {

	@Resource
	private CustomerDAO customerDAO;

	@RenderMapping
	public String handleRenderRequest(RenderRequest request,
			RenderResponse response, Model model) {
		return "addCustomerDefaultRender";
	}

	@RenderMapping(params = "action=addCustomer")
	public String addCustomerMethod(RenderRequest request,
			RenderResponse response, Model model,
			@RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName,
			@RequestParam("birthDate") String birthDate,
			@RequestParam("IDcode") String IDcode) {

		Date parsedBirthDate;
		try {
			parsedBirthDate = new Date(new SimpleDateFormat("yyyy-MM-dd")
					.parse(birthDate).getTime());
			Customer customer = new Customer(firstName, lastName,
					parsedBirthDate, IDcode);

			CustomerValidator validator = new CustomerValidator();
			Errors errors = new BeanPropertyBindingResult(customer, "customer");
			validator.validate(customer, errors);

			if (errors.hasErrors()) {
				model.addAttribute("errors", errors.getAllErrors());
			} else {
				customerDAO.insert(customer);
				model.addAttribute("success", "Customer successfully added!");
			}
		} catch (ParseException e) {
			model.addAttribute("error", "Wrong date format!");
		} catch (SystemException e) {
			model.addAttribute("error",
					"Couldn't save ID code as Expando value.");
		} catch (PortalException e) {
			model.addAttribute("error",
					"Couldn't save ID code as Expando value.");
		} catch (SQLException e) {
			model.addAttribute("error", "Could not add customer.");
		}

		return "addCustomerDefaultRender";
	}
}
