package com.hospitalmanagement.appointment.dto;

import java.time.LocalDateTime;

public class AppointmentDto {

	 private long appointmentId;
	    private long doctorId;
	    private LocalDateTime appointmentDateTime;
	    private LocalDateTime scheduleAt;
	    private AppointmentStatus appointmentStatus;
	    private Patient patient;
	    private Doctor doctor;
	    
		public AppointmentDto() {
			super();
			// TODO Auto-generated constructor stub
		}
		public long getAppointmentId() {
			return appointmentId;
		}
		public void setAppointmentId(long appointmentId) {
			this.appointmentId = appointmentId;
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
		public LocalDateTime getScheduleAt() {
			return scheduleAt;
		}
		public void setScheduleAt(LocalDateTime scheduleAt) {
			this.scheduleAt = scheduleAt;
		}
		public AppointmentStatus getAppointmentStatus() {
			return appointmentStatus;
		}
		public void setAppointmentStatus(AppointmentStatus appointmentStatus) {
			this.appointmentStatus = appointmentStatus;
		}
		public Patient getPatientDto() {
			return patient;
		}
		public void setPatientDto(Patient patient) {
			this.patient = patient;
		}
		public Doctor getDocotor() {
			return doctor;
		}
	    public void setDoctor(Doctor doctor) {
	    	this.doctor = doctor;
	    }

}
