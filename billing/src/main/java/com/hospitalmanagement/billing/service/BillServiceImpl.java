package com.hospitalmanagement.billing.service;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospitalmanagement.billing.exception.AppointmentNotFoundException;
import com.hospitalmanagement.billing.exception.AppointmentServiceNotAvailableException;
import com.hospitalmanagement.billing.exception.BillIdNotFoundException;
import com.hospitalmanagement.billing.exception.PatientNotFoundException;
import com.hospitalmanagement.billing.exception.PatientServiceNotAvailableException;
import com.hospitalmanagement.billing.model.Bill;
import com.hospitalmanagement.billing.repository.BillRepository;
import com.hospitalmanagement.billing.utill.AppointmentDto;
import com.hospitalmanagement.billing.utill.AppointmentResponse;
import com.hospitalmanagement.billing.utill.BillDto;
import com.hospitalmanagement.billing.utill.PatientDto;
import com.hospitalmanagement.billing.utill.PaymentStatus;
import com.hospitalmanagement.billing.utill.PaymentStatusDto;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class BillServiceImpl implements BillService {
	private static final Logger log = LoggerFactory.getLogger(BillServiceImpl.class);
	@Autowired
	private BillRepository billRepository;
	@Autowired
	private PatientClient patientClient;
	@Autowired
	private AppoinmentClient appointmentClient;

	public BillServiceImpl(BillRepository billRepository, PatientClient patientClient,
			AppoinmentClient appointmentClient) {
		super();
		this.billRepository = billRepository;
		this.patientClient = patientClient;
		this.appointmentClient = appointmentClient;
	}

	@Override
	public Bill createBill(Bill bill) {
		log.info("Creating Bill }");
		// bill.setPaymentStatus(PaymentStatus.PAID);
//	bill.getPaymentDate();
		// bill.setPaymentDate(LocalDate.now());
		log.info("Bill details created with id{}", bill.getBillingId());
		return billRepository.save(bill);

	}

	@Override
	public Bill getBillById(long billingId) throws BillIdNotFoundException {
		log.info("Fetching BillingDetails");
		Bill billById = billRepository.findById(billingId)
				.orElseThrow(() -> new BillIdNotFoundException("Bill Not Generated with Bill ID :-" + billingId));
		log.info("Bill Details {}", billingId);
		return billById;
	}

	@Override
	public List<Bill> getAllBill() {
		log.info("Fetching All Bills");
		List<Bill> allBill = billRepository.findAll();
		return allBill;
	}

	@Override
	public Bill updateBill(Bill bill, long billingId) throws BillIdNotFoundException {
		log.info("Fetching Bill by ID");
		Bill updateBill = billRepository.findById(billingId)
				.orElseThrow(() -> new BillIdNotFoundException("Bill Not Generated with Bill ID :-" + billingId));
		updateBill.setPatientId(bill.getPatientId());
		updateBill.setAppointmentId(bill.getAppointmentId());
		updateBill.setTotalAmount(bill.getTotalAmount());
		updateBill.setPaymentStatus(bill.getPaymentStatus());
		updateBill.setPaymentDate(bill.getPaymentDate());
		log.info("Bill Details Updated with ID {}", billingId);
		return billRepository.save(updateBill);
	}

	@Override
	public boolean deleteBill(long billingId) throws BillIdNotFoundException {
		billRepository.findById(billingId).map((bill) -> {
			billRepository.delete(bill);
			return true;
		}).orElseThrow(() -> new BillIdNotFoundException("Bill Not Generated with Bill ID :-" + billingId));

		return true;
	}

	@CircuitBreaker(name = "BillByPatientId", fallbackMethod = "fallBackPatient")
	@Override
	public BillDto getPatientById(long patientId) throws PatientNotFoundException {
		PatientDto patient = patientClient.getPatientById(patientId);
		Bill bill = billRepository.findByPatientId(patientId)
				.orElseThrow(() -> new BillIdNotFoundException("Patient Not Registerd with ID :-" + patientId));
		;

		;
		BillDto patientBill = new BillDto();

		patientBill.setBillingId(bill.getBillingId());
		patientBill.setPatientDto(patient);
		patientBill.setTotalAmount(bill.getTotalAmount());
		patientBill.setPaymentStatus(bill.getPaymentStatus());
		patientBill.setPaymentDate(bill.getPaymentDate());

		return patientBill;
	}

	public BillDto fallBackPatient(long patientId, Throwable throwable) throws PatientServiceNotAvailableException {
		throw new PatientServiceNotAvailableException(
				patientId + " - Patient Service is currently unavailable. Please try again later.");

	}

	@CircuitBreaker(name = "Bill by AppointmentById", fallbackMethod = "fallBackAppointment")
	@Override
	public AppointmentResponse getAppointmentId(long appointmentId) throws AppointmentNotFoundException {
		AppointmentDto appoinement = appointmentClient.getAppointmentId(appointmentId);
		Bill appointment1 = billRepository.findByAppointmentId(appointmentId)
				.orElseThrow(() -> new BillIdNotFoundException("Appointment Not Schedule with ID :-" + appointmentId));
		;
		AppointmentResponse appointmentResponse = new AppointmentResponse();
		appointmentResponse.setBillingId(appointment1.getBillingId());
		appointmentResponse.setAppointmentDto(appoinement);
		appointmentResponse.setTotalAmount(appointment1.getTotalAmount());

		return appointmentResponse;
	}

	public AppointmentResponse fallBackAppointment(long appointmentId, Throwable throwable)
			throws AppointmentServiceNotAvailableException {
		throw new AppointmentServiceNotAvailableException(
				"AppointmentService Under Maintance Please try After Sometime");
	}

	@Override
	public Bill updatePayment(PaymentStatusDto paymentStatus, long billingId) throws BillIdNotFoundException {

		Bill updatePayment = billRepository.findById(billingId)
				.orElseThrow(() -> new BillIdNotFoundException("Bill Not Generated with Bill ID :-" + billingId));

		PaymentStatus newStatus = paymentStatus.getPaymentStatus();
		updatePayment.setPaymentStatus(newStatus);

		if (newStatus == PaymentStatus.PAID) {
			updatePayment.setPaymentDate(LocalDate.now());
		} else {
			updatePayment.setPaymentDate(null);
		}

		return billRepository.save(updatePayment);

	}

}
