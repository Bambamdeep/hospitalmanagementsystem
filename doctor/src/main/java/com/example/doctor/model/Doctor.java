package com.example.doctor.model;

import jakarta.persistence.Column;
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
@Table(name = "Doctor_Details")
public class Doctor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "DoctorId")
	private long doctorId;
	@NotEmpty
	@Size(min = 2, max = 10, message = "Enter Doctor Name")
	@Column(name = "DoctorName")
	private String name;
	@Column(name = "Specilization")
	@NotEmpty
	@Size(min = 4, max = 10, message = "Enter Specilization")
	private String specilization;
	@Column(name = "DepartmetId")
	@NotNull(message = "Enter your Department Id")
	private long departmetId;
	@Column(name = "Contact")
	@NotEmpty
	@Pattern(regexp = "^\\+91\\d{10}$", message = "Enter Your PhoneNumber")
	private String phoneNumber;

	public Doctor() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Doctor(long doctorId, String name, String specilization, long departmetId, String phoneNumber) {
		super();
		this.doctorId = doctorId;
		this.name = name;
		this.specilization = specilization;
		this.departmetId = departmetId;
		this.phoneNumber = phoneNumber;
	}

	public long getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(long doctorId) {
		this.doctorId = doctorId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSpecilization() {
		return specilization;
	}

	public void setSpecilization(String specilization) {
		this.specilization = specilization;
	}

	public long getDepartmetId() {
		return departmetId;
	}

	public void setDepartmetId(long departmetId) {
		this.departmetId = departmetId;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Override
	public String toString() {
		return "Doctor [doctorId=" + doctorId + ", name=" + name + ", specilization=" + specilization + ", departmetId="
				+ departmetId + ", phoneNumber=" + phoneNumber + "]";
	}

	
}
