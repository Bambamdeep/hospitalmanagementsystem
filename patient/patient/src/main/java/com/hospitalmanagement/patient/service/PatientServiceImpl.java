package com.hospitalmanagement.patient.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.hospitalmanagement.patient.exception.PatientNotFoundException;
import com.hospitalmanagement.patient.model.Patient;
import com.hospitalmanagement.patient.repository.PatientRepository;

@Service
public class PatientServiceImpl implements PatientService {
	private static final Logger log = LoggerFactory.getLogger(PatientServiceImpl.class);
	@Autowired
	private PatientRepository patientRepository;

	public PatientServiceImpl(PatientRepository patientRepository) {
		super();
		this.patientRepository = patientRepository;
	}

	@Override
	public Patient createPatient(Patient patient) {
		log.info("Creating Patient with Name {}", patient.getName());
		Patient savePatient = patientRepository.save(patient);
		log.info("patient Created Sucessfullt with ID {}", savePatient.getPatientId());
		return savePatient;
	}

	@Override
	public List<Patient> getAllPatient() {
		log.info("Fetching All Patient");
		List<Patient> allPatient = patientRepository.findAll();
		log.info("Total Patient Found{}", allPatient.size());
		return allPatient;
	}

	@Override
	public Patient getPatientById(long patientId) {
		log.info("Searching Patient By Id {}", patientId);
		Patient patiendById = patientRepository.findById(patientId)
				.orElseThrow(() -> new PatientNotFoundException("Patient Not Registered with ID :- " + patientId));
		return patiendById;
	}

	@Override
	public boolean deletePatient(long patientId) {
		log.info("Delete Patient with Id {}", patientId);
		patientRepository.findById(patientId).map(patient -> {
			patientRepository.delete(patient);
			return true;
		}).orElseThrow(() -> new PatientNotFoundException("Patient Not Registered with ID :- " + patientId));
		return true;
	}

	@Override
	public Patient updateCustomer(Patient patient, long patientId) {
		log.info("updating Patient with Id {}", patientId);
		Patient patient1 = patientRepository.findById(patientId)
				.orElseThrow(() -> new PatientNotFoundException("Patient Not Registered with ID :- " + patientId));
		patient1.setName(patient.getName());
		patient1.setAge(patient.getAge());
		patient1.setAddress(patient.getAddress());
		patient1.setGender(patient.getGender());
		patient1.setPhoneNumber(patient.getPhoneNumber());
		return patientRepository.save(patient1);
	}
	

	

	@Override
	public List<Patient> findByName(String name) {
		log.info("Searching by name {}", name);
		List<Patient> findByName = patientRepository.findAll();
		findByName.stream().filter(patient -> patient.getName().toLowerCase().equals(name.toLowerCase()));
		if (findByName.isEmpty()) {
			throw new PatientNotFoundException("Patient is not register with name :-" + name);
		}
		return findByName;
	}

}
