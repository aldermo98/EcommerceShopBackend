package com.springboot.backend.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.backend.model.Orders;
import com.springboot.backend.repository.OrdersRepository;

@RestController
public class OrdersController {
	
	@Autowired
	OrdersRepository ordersRepository;
	
	@PostMapping("/orders")
	void addOrder(@RequestBody Orders order) {
		ordersRepository.save(order);
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
