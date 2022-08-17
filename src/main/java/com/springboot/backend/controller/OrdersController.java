package com.springboot.backend.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.backend.model.Customer;
import com.springboot.backend.model.Orders;
import com.springboot.backend.model.Product;
import com.springboot.backend.model.Vendor;
import com.springboot.backend.repository.CustomerRepository;
import com.springboot.backend.repository.OrdersRepository;
import com.springboot.backend.repository.ProductRepository;
import com.springboot.backend.repository.VendorRepository;

@RestController
public class OrdersController {
	
	@Autowired
	OrdersRepository ordersRepository;
	
	@Autowired
	VendorRepository vendorRepository;
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	ProductRepository productRepository;
	
	@PostMapping("/orders")
	void addOrder(@RequestBody Orders order) {
		order.setPurchaseDate(LocalDate.now());
		ordersRepository.save(order);
		customerRepository.updateBalance( order.getCustomer().getId(), -(order.getQuantity()*order.getProduct().getPrice()) );
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
