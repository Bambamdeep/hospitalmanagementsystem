package com.hospitalmanagement.billing.controller;

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

import com.hospitalmanagement.billing.model.Bill;
import com.hospitalmanagement.billing.service.BillService;

import com.hospitalmanagement.billing.utill.AppointmentResponse;
import com.hospitalmanagement.billing.utill.BillDto;

import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/bill")
public class BillController {
	@Autowired
	private BillService billService;

	public BillController(BillService billService) {
		super();
		this.billService = billService;
	}

	// Creating bill
	@PostMapping("/create")
	@Operation(summary = "Creating Bill")
	public ResponseEntity<Bill> create(@Valid @RequestBody Bill bill) {
		Bill created = billService.createBill(bill);

		return ResponseEntity.status(HttpStatus.CREATED).body(created);
	}

	// getting All bill
	@GetMapping("/all")
	@Operation(summary = "Getting All Bill")
	public ResponseEntity<List<Bill>> allBill() {

		List<Bill> allBill = billService.getAllBill();
		return ResponseEntity.status(HttpStatus.OK).body(allBill);

	}

	// get Bill By ID
	@GetMapping("/id/{billingId}")
	@Operation(summary = "Getting All Bill")
	public ResponseEntity<Bill> getBill(@PathVariable long billingId) {
		Bill getBillId = billService.getBillById(billingId);
		return ResponseEntity.status(HttpStatus.OK).body(getBillId);

	}

	// update Bill
	@PutMapping("/update/{billingId}")
	@Operation(summary = "Update Bill")
	public ResponseEntity<Bill> updateBill(@Valid @RequestBody Bill bill, @PathVariable long billingId) {
		Bill update = billService.updateBill(bill, billingId);
		return ResponseEntity.status(HttpStatus.CREATED).body(update);
	}

	// delete Bill
	@DeleteMapping("/delete/{billingId}")
	@Operation(summary = "Delete Bill")
	public ResponseEntity<Object> delete(@PathVariable long billingId) {
		Map<Object, String> removeBill = new HashMap<>();
		removeBill.put(billService.deleteBill(billingId), "Bill has been sucessfully Deleted with Id" + billingId);
		return ResponseEntity.status(HttpStatus.OK).body(removeBill);
	}

	// get Patient By Id from Billing service
	@GetMapping("/pid/{patientId}")
	@Operation(summary = "Getting Patient Details by Id")
	public ResponseEntity<BillDto> patient(@PathVariable long patientId) {
		BillDto PatientById = billService.getPatientById(patientId);
		return ResponseEntity.status(HttpStatus.OK).body(PatientById);
	}

	// get Appointment By Id Feign Call
	@GetMapping("aid/{appointmentId}")
	@Operation(summary = "Getting Appointment through Bill Servvice")
	public ResponseEntity<AppointmentResponse> getAppointment(@PathVariable long appointmentId) {
		AppointmentResponse appointmentById = billService.getAppointmentId(appointmentId);
		return ResponseEntity.status(HttpStatus.OK).body(appointmentById);

	}

}
