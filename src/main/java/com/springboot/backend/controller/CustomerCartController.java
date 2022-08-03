package com.springboot.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.backend.model.Customer;
import com.springboot.backend.model.CustomerCart;
import com.springboot.backend.repository.CustomerCartRepository;

@RestController
public class CustomerCartController {
	
	@Autowired
	private CustomerCartRepository customerCartRepository;
	
	@PostMapping("/cart")
	public void postCustomer(@RequestBody CustomerCart customerCart) {
		customerCartRepository.save(customerCart);
	}
	
	@GetMapping("/cart")
	public List<CustomerCart> getAllCustomers() {
	
		return customerCartRepository.findAll();
	}
	
	@PutMapping("/cart/{id}")
	public Customer updateCustomer(@PathVariable("id") Long id,
			@RequestBody Customer newCustomer) {
		return null;
		
	}
	
	@DeleteMapping("/cart/{id}")
	public void deleteCustomer(@PathVariable("id") Long id) {
		customerCartRepository.deleteById(id);
	}

}
