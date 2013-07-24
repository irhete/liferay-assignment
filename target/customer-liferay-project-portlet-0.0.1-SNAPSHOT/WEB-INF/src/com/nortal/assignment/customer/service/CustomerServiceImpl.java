package com.nortal.assignment.customer.service;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.nortal.assignment.customer.data.CustomerDAO;
import com.nortal.assignment.customer.data.ExpandoService;
import com.nortal.assignment.customer.model.Customer;

@Service("customerService")
public class CustomerServiceImpl implements CustomerService {
	@Resource
	private CustomerDAO customerDAO;
	@Resource
	private ExpandoService expandoService;

	@Override
	public void addCustomer(Customer customer) throws SystemException,
			PortalException, SQLException {
		customerDAO.insert(customer);
		expandoService.storeValue(customer.getIDcode(), customer.getId());
	}

	@Override
	public List<Customer> getCustomers() throws SystemException,
			PortalException {
		List<Customer> customers = customerDAO.getCustomers();
		for (Customer customer : customers) {
			customer.setIDcode(expandoService.retrieveValue(customer.getId()));
		}
		return customers;
	}

}
