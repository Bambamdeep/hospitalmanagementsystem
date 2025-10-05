package com.hospitalmanagement.billing.utill;

public class PaymentStatusDto {
	private PaymentStatus paymentStatus;

	public PaymentStatusDto(PaymentStatus paymentStatus) {
		super();
		this.paymentStatus = paymentStatus;
	}

	public PaymentStatusDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PaymentStatus getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(PaymentStatus paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	
	

}
