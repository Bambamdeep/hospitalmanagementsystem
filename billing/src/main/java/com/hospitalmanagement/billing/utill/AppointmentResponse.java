package com.hospitalmanagement.billing.utill;

public class AppointmentResponse {
	private long billingId;
	private PatientDto patientDto;
	private AppointmentDto appointmentDto;
	private double totalAmount;
	public AppointmentResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AppointmentResponse(long billingId, PatientDto patientDto, AppointmentDto appointmentDto,
			double totalAmount) {
		super();
		this.billingId = billingId;
		this.patientDto = patientDto;
		this.appointmentDto = appointmentDto;
		this.totalAmount = totalAmount;
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
	public AppointmentDto getAppointmentDto() {
		return appointmentDto;
	}
	public void setAppointmentDto(AppointmentDto appointmentDto) {
		this.appointmentDto = appointmentDto;
	}
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	@Override
	public String toString() {
		return "AppointmentResponse [billingId=" + billingId + ", patientDto=" + patientDto + ", appointmentDto="
				+ appointmentDto + ", totalAmount=" + totalAmount + "]";
	}
	

}
