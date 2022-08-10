package com.springboot.backend.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.backend.model.Vendor;
import com.springboot.backend.repository.VendorRepository;

@RestController
public class VendorController {
	private VendorRepository vendorRepository;

	@PostMapping("/vendor")	
	public void postVendor(@RequestBody Vendor vendor) {
		vendorRepository.save(vendor);
	}
}