package com.nortal.assignment.customer.data;

import java.sql.SQLException;
import java.util.List;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.nortal.assignment.customer.model.Customer;

/**
 * Services for adding and retrieving customers
 */
public interface CustomerDAO {
	  /**
	   * Inserts customer to database
	   * 
	   * @param customer Customer. First name, last name and birth date must not be null
	 * @throws PortalException 
	 * @throws SystemException 
	 * @throws SQLException 
	   */
	public void insert(Customer customer) throws SystemException, PortalException, SQLException;
	
	  /**
	   * Retrieves all customers
	   * 
	   * @return List of customers
	   */
	public List<Customer> getCustomers();
}
