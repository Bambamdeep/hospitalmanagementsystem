package com.hospitalmanagement.billing.model;

import java.time.LocalDate;

import com.hospitalmanagement.billing.utill.PaymentStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
//import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "Billing_Service")
public class Bill {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long billingId;
	//@NotNull(message = "Enter PatientId")
	@Column(name = "PatientId")
	private long patientId;
	//@NotNull(message = "Enter AppointmentId")
	@Column(name = "AppointmentId")
	private long appointmentId;
	//@NotNull(message = "Enter Total Amount")
	@Column(name = "Total Amount")
	private double totalAmount;
	@Enumerated(EnumType.STRING)
	private PaymentStatus paymentStatus;
	private LocalDate paymentDate;

	public Bill() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public Bill(long billingId, long patientId, long appointmentId, double totalAmount, PaymentStatus paymentStatus,
			LocalDate paymentDate) {
		super();
		this.billingId = billingId;
		this.patientId = patientId;
		this.appointmentId = appointmentId;
		this.totalAmount = totalAmount;
		this.paymentStatus = paymentStatus;
		this.paymentDate = paymentDate;
	}




	public long getBillingId() {
		return billingId;
	}

	public void setBillingId(long billingId) {
		this.billingId = billingId;
	}

	public long getPatientId() {
		return patientId;
	}

	public void setPatientId(long patientId) {
		this.patientId = patientId;
	}

	public long getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(long appointmentId) {
		this.appointmentId = appointmentId;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public PaymentStatus getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(PaymentStatus paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public LocalDate getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(LocalDate paymentDate) {
		this.paymentDate = paymentDate;
	}

	@Override
	public String toString() {
		return "Billing [billingId=" + billingId + ", patientId=" + patientId + ", appointmentId=" + appointmentId
				+ ", totalAmount=" + totalAmount + ", paymentStatus=" + paymentStatus + ", paymentDate=" + paymentDate
				+ "]";
	}

}
