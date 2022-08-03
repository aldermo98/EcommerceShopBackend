package com.springboot.backend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.backend.model.Customer;
import com.springboot.backend.repository.CustomerRepository;

@RestController
public class CustomerController {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@PostMapping("/customer")
	public void postCustomer(@RequestBody Customer customer) {
		customerRepository.save(customer);
	}
	
	@GetMapping("/customers")
	public List<Customer> getAllCustomers(@RequestParam("page") Integer page, @RequestParam("size") Integer size) {
	
		if (page<0)
			page = 0;
		
		Pageable pageable=PageRequest.of(page, size);
		return customerRepository.findAll(pageable).getContent();
	}
	
	@GetMapping("/customer/{id}")
	public Customer getCustomerById(@PathVariable("id") Long id) {
		Optional<Customer> optional = customerRepository.findById(id);
		if (optional.isPresent())
			return optional.get();
		throw new RuntimeException("ID is invalid");
	}
	
	@PutMapping("/customer/{id}")
	public Customer updateCustomer(@PathVariable("id") Long id,
			@RequestBody Customer newCustomer) {
		Optional<Customer> optional = customerRepository.findById(id);
		if (optional.isPresent()) {
			Customer existingCustomer = optional.get();
			existingCustomer.setName(newCustomer.getName());
			existingCustomer.setPassword(newCustomer.getPassword());
			existingCustomer.setBalance(newCustomer.getBalance());
			return customerRepository.save(existingCustomer);
		}
		else
			throw new RuntimeException("ID is invalid");
		
	}
	
	@DeleteMapping("/customer/{id}")
	public void deleteCustomer(@PathVariable("id") Long id) {
		customerRepository.deleteById(id);
	}
	
}
