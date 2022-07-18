package com.springboot.backend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.backend.model.Vendor;
import com.springboot.backend.repository.VendorRepository;

@RestController
public class VendorController {

	@Autowired
	private VendorRepository vendorRepository;
	
	@PostMapping("/vendor")
	public void postVendor(@RequestBody Vendor vendor) {
		vendorRepository.save(vendor);
	}
	
	@GetMapping("/vendor")
	public List<Vendor> getAllVendors() {
		return vendorRepository.findAll();
	}
	
	@GetMapping("/vendor/{id}") //<-- path variable : /vendor/7
	public Vendor getVendorById(@PathVariable("id") Long id) { //id=7
		Optional<Vendor> optional = vendorRepository.findById(id);
		if(optional.isPresent())
			return optional.get();
		else
			throw new RuntimeException("ID is invalid");
	}
}
