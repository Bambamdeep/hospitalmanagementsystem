package com.hospitalmanagement.department;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hospitalmanagement.department.model.Department;
import com.hospitalmanagement.department.service.DepartmentService;

@SpringBootTest
public class DepartmentTest {
	@Autowired
	private DepartmentService departmentService;

	private Department createSampleDepartment() {
		Department department = new Department();
		department.setName("Cardiology");
		department.setLocation("Building A");
		return departmentService.createDepartment(department);
	}

	@Test
	public void testCreateDepartment() {
		Department department = createSampleDepartment();
		assertNotNull(department.getDepartmentId());
		assertEquals("Cardiology", department.getName());
	}

	@Test
	public void testGetAllDepartments() {
		createSampleDepartment();
		List<Department> departments = departmentService.getAll();
		assertFalse(departments.isEmpty());
	}

	@Test
	public void testGetDepartmentById() {
		Department savedDepartment = createSampleDepartment();
		Department fetchedDepartment = departmentService.getDepartmentById(savedDepartment.getDepartmentId());
		assertNotNull(fetchedDepartment);
		assertEquals(savedDepartment.getDepartmentId(), fetchedDepartment.getDepartmentId());
	}

	@Test
	public void testDeleteDepartment() {
		Department savedDepartment = createSampleDepartment();
		boolean deleted = departmentService.delateDepartment(savedDepartment.getDepartmentId());
		assertTrue(deleted);
	}

	@Test
	public void testUpdateDepartment() {
		Department updated = new Department();
		updated.setName("Neurology");
		updated.setLocation("Building B");

		Department savedDepartment = createSampleDepartment();
		Department result = departmentService.updateDepartment(updated, savedDepartment.getDepartmentId());

		assertNotNull(result);
		assertEquals("Neurology", result.getName());
		assertEquals("Building B", result.getLocation());
	}
}
