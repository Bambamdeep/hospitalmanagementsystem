package com.hospitalmanagement.billing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.hospitalmanagement.billing.exception.BillIdNotFoundException;
import com.hospitalmanagement.billing.model.Bill;
import com.hospitalmanagement.billing.repository.BillRepository;
import com.hospitalmanagement.billing.service.AppoinmentClient;
import com.hospitalmanagement.billing.service.BillServiceImpl;
import com.hospitalmanagement.billing.service.PatientClient;
import com.hospitalmanagement.billing.utill.PaymentStatus;
import com.hospitalmanagement.billing.utill.PaymentStatusDto;

public class BillServiceTest {
	@Mock
	private BillRepository billRepository;

	@Mock
	private PatientClient patientClient;

	@Mock
	private AppoinmentClient appointmentClient;

	@InjectMocks
	private BillServiceImpl billService;

	private Bill sampleBill;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		sampleBill = new Bill();
		sampleBill.setBillingId(1L);
		sampleBill.setPatientId(101L);
		sampleBill.setAppointmentId(202L);
		sampleBill.setTotalAmount(500.0);
		sampleBill.setPaymentStatus(PaymentStatus.PAID);
	}

	@Test
	public void testCreateBill() {
		when(billRepository.save(sampleBill)).thenReturn(sampleBill);
		Bill created = billService.createBill(sampleBill);
		assertEquals(1L, created.getBillingId());
		verify(billRepository, times(1)).save(sampleBill);
	}

	@Test
	public void testGetBillById_Success() throws BillIdNotFoundException {
		when(billRepository.findById(1L)).thenReturn(Optional.of(sampleBill));
		Bill found = billService.getBillById(1L);
		assertEquals(101L, found.getPatientId());
	}

	@Test
	public void testGetBillById_NotFound() {
		when(billRepository.findById(2L)).thenReturn(Optional.empty());
		assertThrows(BillIdNotFoundException.class, () -> billService.getBillById(2L));
	}

	@Test
	public void testUpdateBill_Success() throws BillIdNotFoundException {
		Bill updatedBill = new Bill();
		updatedBill.setPatientId(102L);
		updatedBill.setAppointmentId(203L);
		updatedBill.setTotalAmount(600.0);
		updatedBill.setPaymentStatus(PaymentStatus.PAID);
		updatedBill.setPaymentDate(LocalDate.now());

		when(billRepository.findById(1L)).thenReturn(Optional.of(sampleBill));
		when(billRepository.save(any(Bill.class))).thenReturn(updatedBill);

		Bill result = billService.updateBill(updatedBill, 1L);
		assertEquals(600.0, result.getTotalAmount());
		assertEquals(PaymentStatus.PAID, result.getPaymentStatus());
	}

	@Test
	public void testDeleteBill_Success() throws BillIdNotFoundException {
		when(billRepository.findById(1L)).thenReturn(Optional.of(sampleBill));
		boolean deleted = billService.deleteBill(1L);
		assertTrue(deleted);
		verify(billRepository, times(1)).delete(sampleBill);
	}

	@Test
	public void testUpdatePayment_PaidStatus() throws BillIdNotFoundException {
		PaymentStatusDto statusDto = new PaymentStatusDto();
		statusDto.setPaymentStatus(PaymentStatus.PAID);

		when(billRepository.findById(1L)).thenReturn(Optional.of(sampleBill));
		when(billRepository.save(any(Bill.class))).thenReturn(sampleBill);

		Bill updated = billService.updatePayment(statusDto, 1L);
		assertEquals(PaymentStatus.PAID, updated.getPaymentStatus());
		assertNotNull(updated.getPaymentDate());
	}
}
