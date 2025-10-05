package com.hospitalmanagement.appointment.dto;

public class Doctor {
	private long doctorId;
    private String name;
    private String specilization;
    private long departmentId;
    private String phoneNumber;
	public Doctor(long doctorId, String name, String specilization, long departmentId, String phoneNumber) {
		super();
		this.doctorId = doctorId;
		this.name = name;
		this.specilization = specilization;
		this.departmentId = departmentId;
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
	public long getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(long departmentId) {
		this.departmentId = departmentId;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
    
    
	

}
