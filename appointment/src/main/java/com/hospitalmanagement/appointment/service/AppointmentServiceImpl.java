package com.hospitalmanagement.appointment.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

import com.hospitalmanagement.appointment.dto.AppointmentDto;
import com.hospitalmanagement.appointment.dto.AppointmentNotificationDto;
import com.hospitalmanagement.appointment.dto.AppointmentStatus;
import com.hospitalmanagement.appointment.dto.Doctor;
import com.hospitalmanagement.appointment.dto.Patient;
import com.hospitalmanagement.appointment.exception.AppointmentNotFoundException;
import com.hospitalmanagement.appointment.exception.DoctorIdNotFoundException;
import com.hospitalmanagement.appointment.exception.DoctorServiceNotAvailableException;
import com.hospitalmanagement.appointment.exception.PatientIdNotFoundException;
import com.hospitalmanagement.appointment.exception.PatientServiceUnavailableException;
import com.hospitalmanagement.appointment.model.Appointment;
import com.hospitalmanagement.appointment.repository.AppointmentRepository;

import feign.FeignException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class AppointmentServiceImpl implements AppointmentService {
	private static final Logger log = LoggerFactory.getLogger(AppointmentServiceImpl.class);
	@Autowired
	private AppointmentRepository appointmentRepository;
	@Autowired
	private PatientClient patientClient;
	@Autowired
	private DoctorClient doctorClient;
	@Autowired
	private StreamBridge streamBridge;

	public AppointmentServiceImpl(AppointmentRepository appointmentRepository, PatientClient patientClient,
			DoctorClient doctorClient, StreamBridge streamBridge) {
		super();
		this.appointmentRepository = appointmentRepository;
		this.patientClient = patientClient;
		this.doctorClient = doctorClient;
		this.streamBridge = streamBridge;
	}

	@Override
	public Appointment scheduleAppointment(Appointment appointment) {
		 log.info("Scheduling Appointment with Patient {}", appointment.getPatientId());

		    appointment.setAppointmentStatus(AppointmentStatus.SCHEDULED);
		    Appointment appointmentCreated = appointmentRepository.save(appointment);

		    log.info("Appointment Successfully Scheduled");
		    log.info("Sending Notification to patient");

		    sendNotification(appointmentCreated);
		    return appointmentCreated;

	}

	private void sendNotification(Appointment appointment) {
		 Appointment sendAppointment = appointmentRepository.findByPatientId(appointment.getPatientId())
			        .orElseThrow(() -> new PatientIdNotFoundException("Patient Not Found"));

			    AppointmentNotificationDto appointmentNotificationDto = new AppointmentNotificationDto();
			    appointmentNotificationDto.setAppointmentId(appointment.getAppointmentId());
			    appointmentNotificationDto.setPatientId(sendAppointment.getPatientId());
			 			    boolean send = streamBridge.send("appointmentCreated-out-0", appointmentNotificationDto);
			    if (send) {
			        System.out.println("Event is successfully sent");
			    } else {
			        System.out.println("Failed to send event");
			    }

	}

	@Override
	public List<Appointment> getAllAppointment() {
		log.info("Fetching All Appoinment");
		List<Appointment> allAppointment = appointmentRepository.findAll();
		log.info("All Appointment fetched {}", allAppointment.size());
		return allAppointment;
	}

	@Override
	public Appointment confirmAppointment(long appointmentId) throws AppointmentNotFoundException {
		Appointment appointmentById = appointmentRepository.findById(appointmentId)
				.orElseThrow(() -> new AppointmentNotFoundException(
						"Appointment is not schedule with this AppointmentId:- " + appointmentId));
		log.info("AppointmentId found with Id {}", appointmentId);

		appointmentById.setAppointmentStatus(AppointmentStatus.CONFIRM);
		log.info("Appointment Confirmed");
		return appointmentRepository.save(appointmentById);
	}

	@Override
	public Appointment updateAppointment(Appointment appointment, long appointmentId) {

		Appointment existingAppointment = appointmentRepository.findById(appointmentId)
				.orElseThrow(() -> new AppointmentNotFoundException("Appointment not found with ID: " + appointmentId));
		log.info("AppointmentId found with Id {}", appointmentId);

		existingAppointment.setAppointmentDateTime(appointment.getAppointmentDateTime());
		existingAppointment.setDoctorId(appointment.getDoctorId());
		existingAppointment.setPatientId(appointment.getPatientId());
		existingAppointment.setAppointmentStatus(appointment.getAppointmentStatus());
		log.info("Appointment update sucessfully with name {}", existingAppointment.getAppointmentId());
		return appointmentRepository.save(existingAppointment);
	}

	@Override
	public Appointment cancelAppointment(long appointmentId) {

		Appointment cancelAppointment = appointmentRepository.findById(appointmentId)
				.orElseThrow(() -> new AppointmentNotFoundException(
						"Appointment is not schedule with this AppointmentId:- " + appointmentId));
		log.info("AppointmentId found with Id {}", appointmentId);
		cancelAppointment.setAppointmentStatus(AppointmentStatus.CANCELLED);
		appointmentRepository.save(cancelAppointment);
		if (cancelAppointment.getAppointmentStatus() == AppointmentStatus.CANCELLED) {
			appointmentRepository.deleteById(appointmentId);
		} else {
			throw new AppointmentNotFoundException("Appointment not cancell:- ");
		}
		log.info("Appoint cancel with AppointmentId{}", appointmentId);

		return cancelAppointment;

	}

	@CircuitBreaker(name = "patientservice", fallbackMethod = "patientFallback")
	@Override
	public AppointmentDto getPatientById(long patientId) throws PatientIdNotFoundException {

		Appointment appointment = appointmentRepository.findByPatientId(patientId)
				.orElseThrow(() -> new PatientIdNotFoundException("Patient Not Found With ID: " + patientId));
		Patient patient = patientClient.getPatientById(patientId);
		AppointmentDto appointmentDto = new AppointmentDto();
		appointmentDto.setAppointmentId(appointment.getAppointmentId());
		// appointmentDto.setDoctorId(appointment.getDoctorId());
		appointmentDto.setAppointmentDateTime(appointment.getAppointmentDateTime());
		appointmentDto.setScheduleAt(appointment.getScheduleAt());
		appointmentDto.setAppointmentStatus(appointment.getAppointmentStatus());
		appointmentDto.setPatientDto(patient);

		return appointmentDto;
	}

	public AppointmentDto patientFallback(long patientId, Throwable t) throws PatientServiceUnavailableException {
		throw new PatientServiceUnavailableException("Patient Service Under Maintance Please Try after sometime");
	}

	@CircuitBreaker(name = "DoctorService", fallbackMethod = "doctorFallBack")
	@Override
	public AppointmentDto getDoctorById(long doctorId) throws DoctorIdNotFoundException {
		AppointmentDto appointmentDto = new AppointmentDto();

		try {
			Appointment appointment = appointmentRepository.findByDoctorId(doctorId);
			log.info("Making feign Call with DocotId{}", doctorId);
			Doctor doctor = doctorClient.getDoctorById(doctorId);

			Patient patient = patientClient.getPatientById(appointment.getPatientId());
			appointmentDto.setAppointmentId(appointment.getAppointmentId());
			appointmentDto.setDoctorId(appointment.getDoctorId());
			appointmentDto.setAppointmentDateTime(appointment.getAppointmentDateTime());
			appointmentDto.setScheduleAt(appointment.getScheduleAt());
			appointmentDto.setAppointmentStatus(appointment.getAppointmentStatus());
			appointmentDto.setPatientDto(patient);
			appointmentDto.setDoctor(doctor);
		} catch (FeignException.NotFound ex) {
			throw new DoctorIdNotFoundException("Doctor not Available with" + doctorId);
		}

		return appointmentDto;
	}

	public AppointmentDto doctorFallBack(long DoctorId, Throwable throwable) throws DoctorServiceNotAvailableException {
		throw new DoctorServiceNotAvailableException("Doctor Service Is under Maintaince Please try after some time");
	}

}
