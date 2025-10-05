package com.hospitalmanagement.billing.utill;

import java.time.LocalDateTime;

public class AppointmentDto {
	private long appointmentId;
	private long patientId;
	private long doctorId;
	private LocalDateTime appointmentDateTime;

	public AppointmentDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AppointmentDto(long appointmentId, long patientId, long doctorId, LocalDateTime appointmentDateTime) {
		super();
		this.appointmentId = appointmentId;
		this.patientId = patientId;
		this.doctorId = doctorId;
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

	public long getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(long doctorId) {
		this.doctorId = doctorId;
	}

	public LocalDateTime getAppointmentDateTime() {
		return appointmentDateTime;
	}

	public void setAppointmentDateTime(LocalDateTime appointmentDateTime) {
		this.appointmentDateTime = appointmentDateTime;
	}

	@Override
	public String toString() {
		return "AppointmentDto [appointmentId=" + appointmentId + ", patientId=" + patientId + ", doctorId=" + doctorId
				+ ", appointmentDateTime=" + appointmentDateTime + "]";
	}

}
