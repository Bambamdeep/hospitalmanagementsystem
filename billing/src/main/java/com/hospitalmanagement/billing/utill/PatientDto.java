package com.hospitalmanagement.billing.utill;

public class PatientDto {
	private long patientId;
	private String name;
	private int age;
	private String gender;
	private String address;
	private String phoneNumber;
	public PatientDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PatientDto(long patientId, String name, int age, String gender, String address, String phoneNumber) {
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
		return "PatientDto [patientId=" + patientId + ", name=" + name + ", age=" + age + ", gender=" + gender
				+ ", address=" + address + ", phoneNumber=" + phoneNumber + "]";
	}
	
}
