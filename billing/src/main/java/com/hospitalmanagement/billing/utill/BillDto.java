package com.hospitalmanagement.billing.utill;

import java.time.LocalDate;

public class BillDto {
	private long billingId;
	private PatientDto patientDto;
	private double totalAmount;
	private PaymentStatus paymentStatus;
	private LocalDate paymentDate;
	

	public BillDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BillDto(long billingId, PatientDto patientDto, double totalAmount, PaymentStatus paymentStatus,
			LocalDate paymentDate) {
		super();
		this.billingId = billingId;
		this.patientDto = patientDto;
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

	public PatientDto getPatientDto() {
		return patientDto;
	}

	public void setPatientDto(PatientDto patientDto) {
		this.patientDto = patientDto;
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
		return "BillDto [billingId=" + billingId + ", patientDto=" + patientDto + ", totalAmount=" + totalAmount
				+ ", paymentStatus=" + paymentStatus + ", paymentDate=" + paymentDate + "]";
	}

}
