package com.hospitalmanagement.billing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hospitalmanagement.billing.model.Bill;
import com.hospitalmanagement.billing.service.BillService;
import com.hospitalmanagement.billing.utill.PaymentStatus;
import com.hospitalmanagement.billing.utill.PaymentStatusDto;

@SpringBootTest
public class BillTest {

	@Autowired
	private BillService billService;

	private Bill createSampleBill() {
		Bill bill = new Bill();
		bill.setPatientId(101L);
		bill.setAppointmentId(202L);
		bill.setTotalAmount(1500.0);
		bill.setPaymentStatus(PaymentStatus.PAID);
		bill.setPaymentDate(null);
		return billService.createBill(bill);
	}

	@Test
	public void testCreateBill() {
		Bill bill = createSampleBill();
		assertNotNull(bill.getBillingId());
		assertEquals(101L, bill.getPatientId());
		assertEquals(202L, bill.getAppointmentId());
		assertEquals(1500.0, bill.getTotalAmount());
		assertEquals(PaymentStatus.PAID, bill.getPaymentStatus());
		assertNull(bill.getPaymentDate());
	}

	@Test
	public void testGetAllBills() {
		createSampleBill();
		List<Bill> bills = billService.getAllBill();
		assertFalse(bills.isEmpty());
	}

	@Test
	public void testGetBillById() throws Exception {
		Bill savedBill = createSampleBill();
		Bill fetchedBill = billService.getBillById(savedBill.getBillingId());
		assertNotNull(fetchedBill);
		assertEquals(savedBill.getBillingId(), fetchedBill.getBillingId());
	}

	@Test
	public void testUpdateBill() throws Exception {
		Bill savedBill = createSampleBill();

		Bill updatedBill = new Bill();
		updatedBill.setPatientId(102L);
		updatedBill.setAppointmentId(203L);
		updatedBill.setTotalAmount(2000.0);
		updatedBill.setPaymentStatus(PaymentStatus.PAID);
		updatedBill.setPaymentDate(LocalDate.now());

		Bill result = billService.updateBill(updatedBill, savedBill.getBillingId());

		assertNotNull(result);
		assertEquals(102L, result.getPatientId());
		assertEquals(203L, result.getAppointmentId());
		assertEquals(2000.0, result.getTotalAmount());
		assertEquals(PaymentStatus.PAID, result.getPaymentStatus());
		assertNotNull(result.getPaymentDate());
	}

	@Test
	public void testDeleteBill() throws Exception {
		Bill savedBill = createSampleBill();
		boolean deleted = billService.deleteBill(savedBill.getBillingId());
		assertTrue(deleted);
	}

	@Test
	public void testUpdatePaymentStatus() throws Exception {
		Bill savedBill = createSampleBill();

		PaymentStatusDto statusDto = new PaymentStatusDto();
		statusDto.setPaymentStatus(PaymentStatus.PAID);

		Bill updated = billService.updatePayment(statusDto, savedBill.getBillingId());

		assertEquals(PaymentStatus.PAID, updated.getPaymentStatus());
		assertNotNull(updated.getPaymentDate());
	}
}
