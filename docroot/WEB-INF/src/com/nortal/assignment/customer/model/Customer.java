package com.nortal.assignment.customer.model;


public class Customer {
	private int id;
	private String firstName;
	private String lastName;
	private String birthDate;
	private String IDcode;

	public Customer(String firstName, String lastName, String birthDate) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
	}

	public Customer(String firstName, String lastName, String birthDate,
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

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
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
