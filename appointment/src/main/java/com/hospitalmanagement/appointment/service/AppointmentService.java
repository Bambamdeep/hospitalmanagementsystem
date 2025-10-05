package com.hospitalmanagement.appointment.service;

import java.util.List;

import com.hospitalmanagement.appointment.dto.AppointmentDto;

import com.hospitalmanagement.appointment.exception.AppointmentNotFoundException;
import com.hospitalmanagement.appointment.exception.DoctorIdNotFoundException;
import com.hospitalmanagement.appointment.exception.PatientIdNotFoundException;
import com.hospitalmanagement.appointment.model.Appointment;

public interface AppointmentService {
	
	public Appointment  scheduleAppointment(Appointment appointment) ;
	
	public List<Appointment> getAllAppointment();
	
	public Appointment confirmAppointment(long appointmentId) throws  AppointmentNotFoundException ;
	
	public Appointment updateAppointment(Appointment appointment, long appointmentId);
	
	public Appointment cancelAppointment(long appointmentId) throws  AppointmentNotFoundException ;
	
	public AppointmentDto getPatientById(long patientId) throws PatientIdNotFoundException;
	
	public  AppointmentDto getDoctorById(long doctorId) throws DoctorIdNotFoundException;
		
	

}
