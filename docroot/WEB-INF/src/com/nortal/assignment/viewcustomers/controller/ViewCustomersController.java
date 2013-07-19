package com.nortal.assignment.viewcustomers.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.nortal.assignment.customer.model.Customer;
import com.nortal.assignment.customer.service.CustomerService;

@Controller(value = "ViewCustomersController")
@RequestMapping("VIEW")
public class ViewCustomersController {

	@Resource
	private CustomerService customerService;

	@RenderMapping
	public String handleRenderRequest(RenderRequest request,
			RenderResponse response, Model model) throws SystemException,
			PortalException {

		List<Customer> customers = customerService.getCustomers();
		model.addAttribute("customers", customers);
		return "viewCustomersDefaultRender";
	}

}
