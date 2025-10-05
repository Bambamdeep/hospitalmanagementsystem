package com.hospitalmanagement.department.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospitalmanagement.department.exception.DepartmentIdNotFoundException;
import com.hospitalmanagement.department.model.Department;
import com.hospitalmanagement.department.repository.DepartmentRepository;
@Service
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	private DepartmentRepository departmentRepository;
	private static final Logger log = LoggerFactory.getLogger(DepartmentServiceImpl.class);

	public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
		super();
		this.departmentRepository = departmentRepository;
	}

	@Override
	public Department createDepartment(Department department) {
		log.info("Creating Department with Name {}", department.getName());
		Department saveDepartment = departmentRepository.save(department);
		log.info("Department Created Sucessfullt with ID {}", saveDepartment.getDepartmentId());
		return saveDepartment;

	}

	@Override
	public Department getDepartmentById(long departmentId) throws DepartmentIdNotFoundException {
		log.info("Fetching DepartemtById");
		Department getdepartemt = departmentRepository.findById(departmentId)
				.orElseThrow(() -> new DepartmentIdNotFoundException("DepartmentId not found with ID:" + departmentId));
		log.info("Fetcheted DepartemtById{}", departmentId);
		return getdepartemt;

	}

	@Override
	public List<Department> getAll() {
		log.info("Fetching All Department");
		List<Department> allDepartment = departmentRepository.findAll();
		return allDepartment;

	}

	@Override
	public Department updateDepartment(Department department, long departmentId) throws DepartmentIdNotFoundException {

		Department updateDepartment = departmentRepository.findById(departmentId)
				.orElseThrow(() -> new DepartmentIdNotFoundException("DepartmentId not found with ID:" + departmentId));
		updateDepartment.setName(department.getName());
		updateDepartment.setLocation(department.getLocation());
		return departmentRepository.save(updateDepartment);
	}

	@Override
	public boolean delateDepartment(long departmentId) throws DepartmentIdNotFoundException {
		log.info("Fetching DepartemtById{}", departmentId);
		departmentRepository.findById(departmentId).map((department) -> {
			log.info("DepartemtById deleted Sucessfully");
			departmentRepository.delete(department);
			return true;

		}).orElseThrow(() -> new DepartmentIdNotFoundException("DepartmentId not found with ID:" + departmentId));

		return true;

	}

}
