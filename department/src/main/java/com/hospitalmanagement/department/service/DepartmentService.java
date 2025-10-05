package com.hospitalmanagement.department.service;

import java.util.List;

import com.hospitalmanagement.department.exception.DepartmentIdNotFoundException;
import com.hospitalmanagement.department.model.Department;

public interface DepartmentService {
	public Department createDepartment(Department department);

	public Department getDepartmentById(long departmentId) throws DepartmentIdNotFoundException;

	public List<Department> getAll();

	public Department updateDepartment(Department department, long departmentId) throws DepartmentIdNotFoundException;

	public boolean delateDepartment(long departmentId) throws DepartmentIdNotFoundException;

}
