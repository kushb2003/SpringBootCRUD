package com.pratik.crud.dto;

/**
 * Request body for updating a user. The id only selects the existing row;
 * only name and sal are copied onto the loaded entity.
 */
public class UpdateUserRequest {

	private Integer id;
	private String name;
	private String sal;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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
