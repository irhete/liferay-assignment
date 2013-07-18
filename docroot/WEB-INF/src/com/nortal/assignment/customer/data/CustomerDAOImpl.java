package com.nortal.assignment.customer.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.nortal.assignment.customer.model.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {
	@Resource(name = "jdbcTemplate")
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private ExpandoService expandoService;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void insert(final Customer customer) throws SystemException,
			PortalException, SQLException {
		SimpleJdbcInsert insertCustomer = new SimpleJdbcInsert(jdbcTemplate)
				.withTableName("customer").usingGeneratedKeyColumns("id");
		SqlParameterSource parameters = new BeanPropertySqlParameterSource(
				customer);
		int id = insertCustomer.executeAndReturnKey(parameters).intValue();
		customer.setId(id);

		expandoService.storeValue(customer.getIDcode(), id);
	}

	@Override
	public List<Customer> getCustomers() {
		String sql = "select * from customer";
		List<Customer> customers = jdbcTemplate.query(sql,
				new CustomerRowMapper(expandoService));
		return customers;
	}

	private static class CustomerRowMapper implements RowMapper<Customer> {

		private ExpandoService expandoService;

		private CustomerRowMapper(ExpandoService expandoService) {
			this.expandoService = expandoService;
		}

		@Override
		public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
			Customer customer = new Customer();
			customer.setId(rs.getInt("id"));
			customer.setFirstName(rs.getString("firstName"));
			customer.setLastName(rs.getString("lastName"));
			customer.setBirthDate(rs.getDate("birthDate"));
			try {
				customer.setIDcode(expandoService.retrieveValue(customer
						.getId()));
			} catch (PortalException e) {
				e.printStackTrace();
			} catch (SystemException e) {
				e.printStackTrace();
			}
			return customer;
		}
	}

}
