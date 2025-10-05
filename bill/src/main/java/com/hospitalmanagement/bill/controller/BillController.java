package com.hospitalmanagement.bill.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bill")
public class BillController {
	@PostMapping("/save")
	public String name(@RequestBody String bill) {
		
		return bill;
		
	}

}
