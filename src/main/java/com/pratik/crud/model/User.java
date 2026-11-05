package com.pratik.crud.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User implements Serializable{
	
	 @Id
	 @GeneratedValue(strategy=GenerationType.AUTO)
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
		// Only the generated id is included: name and sal are personal data and must not
		// reach logs or consoles through implicit string conversion. Since the id is an int,
		// the output cannot carry CR/LF from user input.
		return "User [id=" + id + "]";
	}
	 
	 

}
