package com.pratik.crud.dto;

/**
 * Request body for creating a user. Deliberately has no id field so the
 * persistence layer always generates the identifier and save() always inserts.
 */
public class CreateUserRequest {

	private String name;
	private String sal;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSal() {
		return sal;
	}

	public void setSal(String sal) {
		this.sal = sal;
	}
}
