package com.nortal.assignment.customer.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.nortal.assignment.customer.model.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {
	@Resource(name = "jdbcTemplate")
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void insert(final Customer customer) throws SQLException {
		SimpleJdbcInsert insertCustomer = new SimpleJdbcInsert(jdbcTemplate)
				.withTableName("customer").usingGeneratedKeyColumns("id");
		SqlParameterSource parameters = new BeanPropertySqlParameterSource(
				customer);
		int id = insertCustomer.executeAndReturnKey(parameters).intValue();
		customer.setId(id);
	}

	@Override
	public List<Customer> getCustomers() {
		String sql = "select * from customer";
		List<Customer> customers = jdbcTemplate.query(sql,
				new CustomerRowMapper());
		return customers;
	}

	private static class CustomerRowMapper implements RowMapper<Customer> {

		@Override
		public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
			Customer customer = new Customer();
			customer.setId(rs.getInt("id"));
			customer.setFirstName(rs.getString("firstName"));
			customer.setLastName(rs.getString("lastName"));
			String birthDateString = new SimpleDateFormat("yyyy-MM-dd")
					.format(rs.getDate("birthDate"));
			customer.setBirthDate(birthDateString);
			return customer;
		}
	}

}
