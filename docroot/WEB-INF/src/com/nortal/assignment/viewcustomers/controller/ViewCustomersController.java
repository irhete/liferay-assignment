package com.nortal.assignment.viewcustomers.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

import com.nortal.assignment.customer.data.CustomerDAO;
import com.nortal.assignment.customer.model.Customer;

@Controller(value = "ViewCustomersController")
@RequestMapping("VIEW")
public class ViewCustomersController {

	@Resource
	private CustomerDAO customerDAO;

	@RenderMapping
	public String handleRenderRequest(RenderRequest request,
			RenderResponse response, Model model) {

		List<Customer> customers = customerDAO.getCustomers();
		model.addAttribute("customers", customers);
		return "viewCustomersDefaultRender";
	}

}
