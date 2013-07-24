package com.nortal.assignment.customer.model;

import java.util.Date;

public class Customer {
	private int id;
	private String firstName;
	private String lastName;
	private Date birthDate;
	private String IDcode;

	public Customer(String firstName, String lastName, Date birthDate) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
	}

	public Customer(String firstName, String lastName, Date birthDate,
			String IDcode) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.IDcode = IDcode;
	}

	public Customer() {
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getIDcode() {
		return IDcode;
	}

	public void setIDcode(String iDcode) {
		IDcode = iDcode;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
