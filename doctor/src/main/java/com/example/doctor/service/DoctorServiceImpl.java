package com.example.doctor.service;

import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.doctor.exception.DoctorIdNotFoundException;
import com.example.doctor.exception.DoctorNotFoundException;
import com.example.doctor.model.Doctor;
import com.example.doctor.repository.DoctorRepository;

@Service
public class DoctorServiceImpl implements DoctorService {
	private static final Logger log = LoggerFactory.getLogger(DoctorServiceImpl.class);
	@Autowired
	private DoctorRepository doctorRepository;

	public DoctorServiceImpl(DoctorRepository doctorRepository) {
		super();
		this.doctorRepository = doctorRepository;
	}

	@Override
	public Doctor createDoctor(Doctor doctor) {
		log.info("Creating Doctor with Name {}", doctor.getName());
		Doctor saveDoctor = doctorRepository.save(doctor);
		log.info("Doctor Created Sucessfullt with ID {}", saveDoctor.getDoctorId());
		return saveDoctor;
	}

	@Override
	public List<Doctor> getAllDoctor() {
		log.info("Fetching All Doctor");
		List<Doctor> doctors = doctorRepository.findAll();
		log.info("All Doctor {}", doctors.size());

		return doctors;
	}

	@Override
	public Doctor getDoctorById(long doctorId) {

		Doctor doctorById = doctorRepository.findById(doctorId)
				.orElseThrow(() -> new DoctorIdNotFoundException("Doctor not found with Id :-" + doctorId));
		log.info("fetching doctor By Id {}", doctorId);
		return doctorById;
	}

	@Override
	public Doctor updateDoctor(Doctor doctor, long doctorId) {
		log.info("updating doctor with id {}", doctorId);
		Doctor updateDoctor = doctorRepository.findById(doctorId)
				.orElseThrow(() -> new DoctorIdNotFoundException("Doctor not found with Id :-" + doctorId));
		updateDoctor.setName(doctor.getName());
		updateDoctor.setSpecilization(doctor.getSpecilization());
		updateDoctor.setDepartmetId(doctor.getDepartmetId());
		updateDoctor.setPhoneNumber(doctor.getPhoneNumber());
		log.info("Doctor updated sucessfull with Id {}", doctorId);
		return doctorRepository.save(updateDoctor);
	}

	@Override
	public boolean deleteDoctor(long doctorId) {
		doctorRepository.findById(doctorId).map(doctor -> {
			doctorRepository.delete(doctor);
			return true;

		}).orElseThrow(() -> new DoctorIdNotFoundException("Doctor not found with Id :-" + doctorId));
		log.info("Delete Docotr with Id {}", doctorId);
		return true;
	}

	@Override
	public List<Doctor> findBySpecilization(String specilization) {
		log.debug("All Docotr{}",doctorRepository.findAll());

List<Doctor> doctors = doctorRepository.findBySpecilization(specilization);
    if (doctors.isEmpty()) {
        throw new DoctorNotFoundException("No doctors found with specialization: " + specilization);
    }
    log.info("Doctor is available with Specilization{}", specilization);
    return doctors;

		
	
	}

	@Override
	public List<Doctor> findByDepartmetId(long departemtId) {

		List<Doctor> doctors = doctorRepository.findByDepartmetId(departemtId);
		if (doctors.isEmpty()) {
			throw new DoctorNotFoundException("No doctors found for department ID: " + departemtId);
		}
		log.info("Doctor with depatmetn {}", departemtId);
		return doctors;

	}

}
