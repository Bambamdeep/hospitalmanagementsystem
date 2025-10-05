package com.hospitalmanagement.department.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "Department_Details")
public class Department {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long departmentId;
	@NotEmpty(message= "Department Name is Required")
	@Size(min = 3 ,max = 10,message = "Department Name must between min 3 to 10 character")
	@Column(name = "Department_Name")
	private String name;
	@NotEmpty(message= "Location is Required")
	@Size(min = 3 ,max = 10,message = "Location must between min 3 to 10 character")
	@Column(name = "Location")
	private String location;
	public Department() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Department(long departmentId,
			@NotEmpty(message = "Department Name is Required") @Size(min = 3, max = 10, message = "Department Name must between min 3 to 10 character") String name,
			@NotEmpty(message = "Location is Required") @Size(min = 3, max = 10, message = "Location must between min 3 to 10 character") String location) {
		super();
		this.departmentId = departmentId;
		this.name = name;
		this.location = location;
	}
	public long getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(long departmentId) {
		this.departmentId = departmentId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	@Override
	public String toString() {
		return "Department [departmentId=" + departmentId + ", name=" + name + ", location=" + location + "]";
	}
	


}
