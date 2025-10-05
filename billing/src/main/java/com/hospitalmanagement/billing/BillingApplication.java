package com.hospitalmanagement.billing;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import com.hospitalmanagement.billing.model.Bill;
import com.hospitalmanagement.billing.service.BillService;
import com.hospitalmanagement.billing.utill.PaymentStatus;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients("com.hospitalmanagement.billing.service")
public class BillingApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(BillingApplication.class, args);
	}
	@Autowired
	private BillService billService;
	

	public BillingApplication(BillService billService) {
		super();
		this.billService = billService;
	}


	@Override
	public void run(String... args) throws Exception {
	//	Bill bill = new Bill(101,1,1,300,PaymentStatus.PAID, LocalDate.now());
	//	billService.createBill(bill);



		
	}

}
