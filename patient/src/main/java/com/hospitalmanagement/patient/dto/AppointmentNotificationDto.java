package com.hospitalmanagement.patient.dto;

import java.time.LocalDateTime;

public class AppointmentNotificationDto {
	private long appointmentId;;
	private long patientId;
	private String phone;
	private LocalDateTime appointmentDateTime;
	public AppointmentNotificationDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AppointmentNotificationDto(long appointmentId, long patientId, String phone,
			LocalDateTime appointmentDateTime) {
		super();
		this.appointmentId = appointmentId;
		this.patientId = patientId;
		this.phone = phone;
		this.appointmentDateTime = appointmentDateTime;
	}
	public long getAppointmentId() {
		return appointmentId;
	}
	public void setAppointmentId(long appointmentId) {
		this.appointmentId = appointmentId;
	}
	public long getPatientId() {
		return patientId;
	}
	public void setPatientId(long patientId) {
		this.patientId = patientId;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public LocalDateTime getAppointmentDateTime() {
		return appointmentDateTime;
	}
	public void setAppointmentDateTime(LocalDateTime appointmentDateTime) {
		this.appointmentDateTime = appointmentDateTime;
	}
	@Override
	public String toString() {
		return "AppointmentNotificationDto [appointmentId=" + appointmentId + ", patientId=" + patientId + ", phone="
				+ phone + ", appointmentDateTime=" + appointmentDateTime + "]";
	}

}
