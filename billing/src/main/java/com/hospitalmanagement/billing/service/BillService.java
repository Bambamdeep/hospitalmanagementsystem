package com.hospitalmanagement.billing.service;

import java.util.List;

import com.hospitalmanagement.billing.exception.AppointmentNotFoundException;
import com.hospitalmanagement.billing.exception.BillIdNotFoundException;
import com.hospitalmanagement.billing.exception.PatientNotFoundException;
import com.hospitalmanagement.billing.model.Bill;

import com.hospitalmanagement.billing.utill.AppointmentResponse;
import com.hospitalmanagement.billing.utill.BillDto;

import com.hospitalmanagement.billing.utill.PaymentStatusDto;

public interface BillService {

	public Bill createBill(Bill bill);

	public Bill getBillById(long billingId) throws BillIdNotFoundException;

	public List<Bill> getAllBill();

	public Bill updateBill(Bill bill, long billingId) throws BillIdNotFoundException;

	public boolean deleteBill(long billingId) throws BillIdNotFoundException;

	public BillDto getPatientById(long patientId) throws PatientNotFoundException;

	public AppointmentResponse getAppointmentId(long appointmentId) throws AppointmentNotFoundException;

	public Bill updatePayment(PaymentStatusDto paymentStatus, long billingId) throws BillIdNotFoundException;

}
