package com.springboot.backend.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.backend.model.Customer;
import com.springboot.backend.model.Orders;
import com.springboot.backend.model.Vendor;
import com.springboot.backend.repository.CustomerRepository;
import com.springboot.backend.repository.OrdersRepository;
import com.springboot.backend.repository.VendorRepository;

@RestController
public class OrdersController {
	
	@Autowired
	OrdersRepository ordersRepository;
	
	@Autowired
	VendorRepository vendorRepository;
	
	@Autowired
	CustomerRepository customerRepository;
	
	@PostMapping("/orders")
	void addOrder(@RequestBody Orders order) {
		ordersRepository.save(order);
		
		Optional<Vendor> optionalV = vendorRepository.findById(order.getVendorId());
		if(optionalV.isPresent()) {
			Vendor existingVendor = optionalV.get();
			existingVendor.setBalance( existingVendor.getBalance() + (order.getQuantity()*order.getPrice()) );
			vendorRepository.save(existingVendor);
		}
		else	
			throw new RuntimeException("Vendor ID is invalid");
		
//		Optional<Customer> optionalC = customerRepository.findById(order.getCustomerId());
//		if(optionalC.isPresent()) {
//			Customer existingCustomer = optionalC.get();
//			existingCustomer.setBalance( existingCustomer.getBalance() - (order.getQuantity()*order.getPrice()) );
//			customerRepository.save(existingCustomer);
//		}
//		else	
//			throw new RuntimeException("Customer ID is invalid");
		
		customerRepository.updateBalance( order.getCustomerId(), -(order.getQuantity()*order.getPrice()) );
	}
	
	@GetMapping("/orders/customer/{customerId}")
	List<Orders> getOrdersByCustomer(@PathVariable("customerId") Long cid){
		return ordersRepository.getOrdersByCustomer(cid);
	}
	
	@GetMapping("/orders/vendor/{vendorId}")
	List<Orders> getOrdersByVendor(@PathVariable("vendorId") Long vid){
		return ordersRepository.getOrdersByVendor(vid);
	}
	
}
