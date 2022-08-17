package com.springboot.backend.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.springboot.backend.model.Customer;
import com.springboot.backend.model.CustomerCart;
import com.springboot.backend.model.Product;
import com.springboot.backend.model.Purchases;
import com.springboot.backend.repository.CustomerCartRepository;
import com.springboot.backend.repository.CustomerRepository;
import com.springboot.backend.repository.PurchasesRepository;

public class PurchasesController {
	@Autowired
	private PurchasesRepository purchasesRepository;
	
	@Autowired
	private CustomerCartRepository customerCartRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@PostMapping("/purchase/{id}")
	public void makePurchase() {
		
	}

}
