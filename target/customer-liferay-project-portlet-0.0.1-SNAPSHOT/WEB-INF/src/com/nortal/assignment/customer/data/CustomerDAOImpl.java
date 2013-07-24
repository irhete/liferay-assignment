package com.nortal.assignment.customer.data;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
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
				new BeanPropertyRowMapper<Customer>(Customer.class));
		return customers;
	}

}
