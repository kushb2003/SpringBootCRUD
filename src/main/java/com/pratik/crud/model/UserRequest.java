package com.pratik.crud.model;

/**
 * Client-editable fields of a User. Deliberately has no id: the primary key is
 * server-generated on create and taken from the authorized request path on update.
 */
public class UserRequest {

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
