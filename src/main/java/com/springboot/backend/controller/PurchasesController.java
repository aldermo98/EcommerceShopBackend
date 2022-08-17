package com.springboot.backend.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.springboot.backend.model.CustomerCart;
import com.springboot.backend.repository.CustomerCartRepository;
import com.springboot.backend.repository.PurchasesRepository;

public class PurchasesController {
	@Autowired
	private PurchasesRepository purchasesRepository;
	
	@Autowired
	private CustomerCartRepository customerCartRepository;
	
	@PostMapping("/purchase/cart/{cid}")
	public void makePurchase(@PathVariable("cid") Long cid) {
		Optional<CustomerCart> optional = customerCartRepository.findById(cid);
		if (optional.isPresent()) {
			
		}
		else
			throw new RuntimeException("CustomerCart id invalid");
	}

}
