package com.nortal.assignment.customer.service;

import java.sql.SQLException;
import java.util.List;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.nortal.assignment.customer.model.Customer;

public interface CustomerService {
	public void addCustomer(Customer customer) throws SystemException,
			PortalException, SQLException;

	public List<Customer> getCustomers() throws SystemException,
			PortalException;
}
