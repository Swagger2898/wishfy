package com.example.wishfy.model;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

@Entity
@Table(name="employee")
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column
	@NotBlank(message ="Name is mandatory")
	private String name;
	
	@Column
	@Email(message="invalid email id")
	private String email;
	
	@Column
	@NotBlank(message = "Department can't be blank")
	private String department; 
	
	@Column
	@Past(message=" date cannot be of future")
	private Date joiningDate;
	
	
	
	
	  public Employee(int id, String name, String email, String department, Date joiningDate) {
	        this.id = id;
	        this.name = name;
	        this.email = email;
	        this.department = department;
	        this.joiningDate = joiningDate;
	    }
	  
	  
	  
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
	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	
	
	public Date getJoiningDate() {
		return joiningDate;
	}
	public void setJoiningDate(Date joiningDate) {
		this.joiningDate = joiningDate;
	}
	
	

}
