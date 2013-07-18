package com.nortal.assignment.customer.test;

import static org.junit.Assert.assertEquals;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.nortal.assignment.customer.data.CustomerDAOImpl;
import com.nortal.assignment.customer.data.ExpandoService;
import com.nortal.assignment.customer.model.Customer;

public class CustomerDAOtests {
	@Mock
	ExpandoService expandoService;
	@InjectMocks
	CustomerDAOImpl customerDAO;

	private EmbeddedDatabase database;

	@Before
	public void setUp() throws SQLException, ParseException {
		database = new EmbeddedDatabaseBuilder().addScript("schema.sql")
				.addScript("test-data.sql").build();
		configureDB();
		MockitoAnnotations.initMocks(this);
	}

	@After
	public void tearDown() throws Exception {
		database.shutdown();
	}

	private void configureDB() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(database);
		customerDAO = new CustomerDAOImpl();
		customerDAO.setJdbcTemplate(jdbcTemplate);
	}

	@Test
	public void addCustomerSuccessfulTest() throws ParseException,
			SQLException, SystemException, PortalException {
		Customer customer = new Customer("new", "customer", getValidDate(), "");
		Mockito.doNothing().when(expandoService).storeValue("", 1);
		customerDAO.insert(customer);
		assertEquals(3, customer.getId());
	}

	@Test(expected = DataIntegrityViolationException.class)
	public void addCustomerFirstNameNullTest() throws ParseException,
			SQLException, SystemException, PortalException {
		Customer customer = new Customer();
		customer.setLastName("customer");
		customer.setBirthDate(getValidDate());
		customerDAO.insert(customer);
	}

	private Date getValidDate() throws ParseException {
		Date parsedBirthDate = new Date(new SimpleDateFormat("yyyy-MM-dd")
				.parse("1990-8-22").getTime());
		return parsedBirthDate;
	}

	@Test
	public void getCustomersTest() throws SystemException, PortalException,
			ParseException, SQLException {
		Mockito.when(expandoService.retrieveValue(1)).thenReturn("");
		assertEquals(2, customerDAO.getCustomers().size());
	}

}
