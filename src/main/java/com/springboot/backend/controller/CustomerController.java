package com.springboot.backend.controller;

import org.springframework.web.bind.annotation.RestController;

import com.springboot.backend.repository.CustomerRepository;

@RestController
public class CustomerController {
	private CustomerRepository customerRepository;

}
