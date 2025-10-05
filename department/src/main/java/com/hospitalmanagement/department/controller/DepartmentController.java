package com.hospitalmanagement.department.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hospitalmanagement.department.model.Department;
import com.hospitalmanagement.department.service.DepartmentService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/department")
public class DepartmentController {
	@Autowired
	private DepartmentService departmentService;

	public DepartmentController(DepartmentService departmentService) {
		super();
		this.departmentService = departmentService;
	}
	// Creating Departemnt
		@PostMapping("/create")
		@Operation(summary = "Creating Deparment Service ")
		public ResponseEntity<Department> create(@Valid @RequestBody Department department) {
			Department create = departmentService.createDepartment(department);
			return ResponseEntity.status(HttpStatus.CREATED).body(create);
		}

		// getting DepartmentByID
		@GetMapping("/id/{departmentId}")
		@Operation(summary = "Getting  DepartmentById")
		public ResponseEntity<Department> departmentById(@PathVariable long departmentId) {
			Department getdepartment = departmentService.getDepartmentById(departmentId);

			return ResponseEntity.status(HttpStatus.OK).body(getdepartment);
		}

		// Getting All Department
		@GetMapping("all")
		@Operation(summary = "Getting All Department")
		public ResponseEntity<List<Department>> allDepartemt() {
			List<Department> departmentAll = departmentService.getAll();
			return ResponseEntity.status(HttpStatus.OK).body(departmentAll);
		}
		
		// Update Department
		@PutMapping("/update/{departmentId}")
		@Operation(summary = "Updating Department")
		public ResponseEntity<Department> update (@Valid @RequestBody Department department , @PathVariable long departmentId){
			Department updateDepartment = departmentService.updateDepartment(department, departmentId);
			return ResponseEntity.status(HttpStatus.OK).body(updateDepartment);
		}
	// Delete Department
		@DeleteMapping("/delete/{departmentId}")
		@Operation(summary = "Updating Department")
		public ResponseEntity<Object> delte (@PathVariable long departmentId){
			Map<Object,String> remove = new HashMap<>();
			remove.put(departmentService.delateDepartment(departmentId), "DeparmtentId Sucessfully Removed"+ departmentId);
			return  ResponseEntity.status(HttpStatus.OK).body(remove);
		}


}
