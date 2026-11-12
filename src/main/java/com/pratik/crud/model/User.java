package com.pratik.crud.model;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class User implements Serializable{
	
	 @Id
	 @GeneratedValue(strategy=GenerationType.AUTO)
	 // The primary key is assigned by the server and is never bound from a request body.
	 @JsonProperty(access = JsonProperty.Access.READ_ONLY)
	 private int id;
	 private String name;
	 private String sal;
	public int getId() {
		return id;
	}
	public void setId(int id) {
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
	@Override
	public String toString() {
		// Salary is deliberately excluded so it never reaches logs or console output.
		return "User [id=" + id + ", name=" + name + "]";
	}
	 
	 

}
