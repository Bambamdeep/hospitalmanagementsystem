package com.hospitalmanagement.appointment.model;

import java.time.LocalDateTime;

import com.hospitalmanagement.appointment.dto.AppointmentStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "Appointment_Details")
public class Appointment {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	//@NotNull
	@Column(name = "AppointmentId")
	private long appointmentId;
	@Column(name = "PatientId")
	//@NotNull(message = "Enter PatientId")
	private long patientId;
	@Column(name = "DoctorId")
	//@NotNull(message = "Enter DoctorId")
	private long doctorId;
	private LocalDateTime scheduleAt;
	//@NotNull(message = "Appointment date/time is required")
	//@Future(message = "Appointment date/time must be in the future")
	private LocalDateTime appointmentDateTime;
	
	@Enumerated(EnumType.STRING)
	private AppointmentStatus appointmentStatus;
	

@PrePersist
    protected void onSchedule() {
        this.scheduleAt = LocalDateTime.now();
    }


public Appointment() {
	super();
	// TODO Auto-generated constructor stub
}

//AppointmentStatus appointmentStatus
public Appointment(long appointmentId, @NotNull(message = "Enter PatientId") long patientId,
		@NotNull(message = "Enter DoctorId") long doctorId,
		@NotNull(message = "Appointment date/time is required") @Future(message = "Appointment date/time must be in the future") LocalDateTime appointmentDateTime
		) {
	super();
	this.appointmentId = appointmentId;
	this.patientId = patientId;
	this.doctorId = doctorId;
	this.appointmentDateTime = appointmentDateTime;
	//this.appointmentStatus = appointmentStatus;
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


public LocalDateTime getScheduleAt() {
	return scheduleAt;
}


public void setScheduleAt(LocalDateTime scheduleAt) {
	this.scheduleAt = scheduleAt;
}


public LocalDateTime getAppointmentDateTime() {
	return appointmentDateTime;
}


public void setAppointmentDateTime(LocalDateTime appointmentDateTime) {
	this.appointmentDateTime = appointmentDateTime;
}


public AppointmentStatus getAppointmentStatus() {
	return appointmentStatus;
}


public void setAppointmentStatus(AppointmentStatus appointmentStatus) {
	this.appointmentStatus = appointmentStatus;
}


@Override
public String toString() {
	return "Appointment [appointmentId=" + appointmentId + ", patientId=" + patientId + ", doctorId=" + doctorId
			+ ", scheduleAt=" + scheduleAt + ", appointmentDateTime=" + appointmentDateTime + ", appointmentStatus="
			+ appointmentStatus + "]";
}


	

}