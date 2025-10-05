package com.hospitalmanagement.department;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.hospitalmanagement.department.exception.DepartmentIdNotFoundException;
import com.hospitalmanagement.department.model.Department;
import com.hospitalmanagement.department.repository.DepartmentRepository;
import com.hospitalmanagement.department.service.DepartmentServiceImpl;

public class DepartmentServiceTest {

	@Mock
	private DepartmentRepository departmentRepository;

	@InjectMocks
	private DepartmentServiceImpl departmentService;

	private Department department;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		department = new Department();
		department.setDepartmentId(1L);
		department.setName("Cardiology");
		department.setLocation("Building A");
	}

	@Test
	void testCreateDepartment() {
		when(departmentRepository.save(department)).thenReturn(department);
		Department result = departmentService.createDepartment(department);
		assertNotNull(result);
		assertEquals("Cardiology", result.getName());
	}

	@Test
	void testGetDepartmentById_Found() {
		when(departmentRepository.findById(1L)).thenReturn(Optional.of(department));
		Department result = departmentService.getDepartmentById(1L);
		assertEquals(1L, result.getDepartmentId());
	}

	@Test
	void testGetDepartmentById_NotFound() {
		when(departmentRepository.findById(1L)).thenReturn(Optional.empty());
		assertThrows(DepartmentIdNotFoundException.class, () -> departmentService.getDepartmentById(1L));
	}

	@Test
	void testGetAllDepartments() {
		when(departmentRepository.findAll()).thenReturn(List.of(department));
		List<Department> result = departmentService.getAll();
		assertFalse(result.isEmpty());
	}

	@Test
	void testUpdateDepartment() {
		Department updated = new Department();
		updated.setName("Neurology");
		updated.setLocation("Building B");

		when(departmentRepository.findById(1L)).thenReturn(Optional.of(department));
		when(departmentRepository.save(any(Department.class))).thenReturn(updated);

		Department result = departmentService.updateDepartment(updated, 1L);
		assertEquals("Neurology", result.getName());
		assertEquals("Building B", result.getLocation());
	}

	@Test
	void testDelateDepartment_Success() {
		when(departmentRepository.findById(1L)).thenReturn(Optional.of(department));
		boolean result = departmentService.delateDepartment(1L);
		assertTrue(result);
		verify(departmentRepository).delete(department);
	}

	@Test
	void testDelateDepartment_NotFound() {
		when(departmentRepository.findById(1L)).thenReturn(Optional.empty());
		assertThrows(DepartmentIdNotFoundException.class, () -> departmentService.delateDepartment(1L));
	}
}
