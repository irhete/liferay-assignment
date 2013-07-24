package com.nortal.assignment.customer.data;

import org.springframework.stereotype.Service;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.ClassNameLocalServiceUtil;
import com.liferay.portal.service.CompanyLocalServiceUtil;
import com.liferay.portlet.expando.NoSuchTableException;
import com.liferay.portlet.expando.model.ExpandoColumn;
import com.liferay.portlet.expando.model.ExpandoColumnConstants;
import com.liferay.portlet.expando.service.ExpandoColumnLocalServiceUtil;
import com.liferay.portlet.expando.service.ExpandoTableLocalServiceUtil;
import com.liferay.portlet.expando.service.ExpandoValueLocalServiceUtil;
import com.nortal.assignment.customer.model.Customer;

@Service("expandoService")
public class ExpandoServiceImpl implements ExpandoService {
	private final String COLUMN_NAME = "IDcode";
	private final String CLASS_NAME = Customer.class.getName();

	private long tableId;
	private long companyId;
	private long columnId;
	private long classNameId;

	private void init() throws SystemException, PortalException {
		companyId = CompanyLocalServiceUtil.getCompanies().get(0)
				.getCompanyId();
		classNameId = ClassNameLocalServiceUtil.getClassNameId(CLASS_NAME);
		getAndSetTableId();	
		getAndSetColumnId();
	}

	private void getAndSetTableId() throws PortalException,
			SystemException {
		try {
			tableId = ExpandoTableLocalServiceUtil.getDefaultTable(companyId, classNameId).getTableId();
		} catch (NoSuchTableException e) {
			tableId = ExpandoTableLocalServiceUtil.addDefaultTable(companyId,
					classNameId).getTableId();
		} finally {
			if (tableId == 0) {
				throw new SystemException("Could not get/create expando table");
			}
		}
	}

	private void getAndSetColumnId() throws SystemException,
			PortalException {
		try {
			columnId = ExpandoColumnLocalServiceUtil.getColumn(tableId, COLUMN_NAME).getColumnId();
		} catch (NoSuchTableException e) {
			ExpandoColumn column = ExpandoColumnLocalServiceUtil.addColumn(tableId,
					COLUMN_NAME, ExpandoColumnConstants.STRING);
			columnId = column.getColumnId();
		} finally {
			if (columnId == 0) {
				throw new SystemException("Could not get/create expando column");
			}
		}
	}

	@Override
	public void storeValue(String IDcode, int customerId)
			throws SystemException, PortalException {
		if (columnId == 0) {
			init();
		}
		ExpandoValueLocalServiceUtil.addValue(classNameId, tableId, columnId,
				customerId, IDcode);
	}
	
	@Override
	public String retrieveValue(int customerId)
			throws SystemException, PortalException {
		if (columnId == 0) {
			init();
		}
		return ExpandoValueLocalServiceUtil.getValue(tableId, columnId, customerId).getString();  
	}
}
