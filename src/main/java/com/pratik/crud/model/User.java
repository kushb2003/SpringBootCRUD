package com.pratik.crud.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class User implements Serializable{
	
	 @Id
	 @GeneratedValue(strategy=GenerationType.AUTO)
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
		// Salary is deliberately left out so it cannot reach logs or console output by accident.
		return "User [id=" + id + ", name=" + name + "]";
	}
	 
	 

}
