package com.nortal.assignment.customer.data;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * Services for adding and retrieving IDcode values from Liferay Expando Table
 */
public interface ExpandoService {
	
	/**
	   * Stores IDcode as an Expando Value
	   * 
	   * @param String IDcode
	   * @param int customerId
	 * @throws PortalException 
	 * @throws SystemException 
	   */
	public void storeValue(String IDcode, int customerId) throws SystemException, PortalException;
	
	/**
	   * Retrieves IDcode by customer ID
	   * 
	   * @param int customerId
	 * @throws PortalException 
	 * @throws SystemException 
	 * @return IDcode as String
	   */
	public String retrieveValue(int customerId) throws SystemException, PortalException;
}
