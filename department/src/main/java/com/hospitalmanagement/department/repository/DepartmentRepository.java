package com.hospitalmanagement.department.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hospitalmanagement.department.model.Department;

public interface DepartmentRepository extends JpaRepository<Department,Long>{

}
