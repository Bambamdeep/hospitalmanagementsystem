package com.hospitalmanagement.patient.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "Patient_Details")
public class Patient {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long patientId;
	@NotEmpty(message = "Enter your name")
	@Size(min=3 ,max= 10)
	private String name;
	@NotNull
	private int age;
	@NotNull(message ="Enter your Gender")
	@Size(min=3,max=10)
	private String gender;
	@NotNull(message = "Enter Your address")
	@Size(min=3 ,max=20)
	private String address;
	@NotEmpty
	@Pattern(regexp = "^\\+91\\d{10}$",message= "Enter Your PhoneNumber")
	private String phoneNumber;
	public Patient() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Patient(long patientId, @NotEmpty(message = "Enter your name") @Size(min = 3, max = 10) String name,
			@NotNull int age, @NotNull(message = "Enter your Gender") @Size(min = 3, max = 10) String gender,
			@NotNull(message = "Enter Your address") @Size(min = 3, max = 20) String address,
			@NotEmpty @Pattern(regexp = "^\\+91\\d{10}$", message = "Enter Your PhoneNumber") String phoneNumber) {
		super();
		this.patientId = patientId;
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.address = address;
		this.phoneNumber = phoneNumber;
	}
	public long getPatientId() {
		return patientId;
	}
	public void setPatientId(long patientId) {
		this.patientId = patientId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	@Override
	public String toString() {
		return "Patient [patientId=" + patientId + ", name=" + name + ", age=" + age + ", gender=" + gender
				+ ", address=" + address + ", phoneNumber=" + phoneNumber + "]";
	}
	

}
