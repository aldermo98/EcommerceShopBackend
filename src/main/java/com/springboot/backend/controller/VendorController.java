package com.springboot.backend.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



import com.springboot.backend.model.Orders;
import com.springboot.backend.model.Product;
import com.springboot.backend.model.Vendor;
import com.springboot.backend.repository.UserRepository;
import com.springboot.backend.repository.VendorRepository;

@RestController
public class VendorController {
	@Autowired
	private VendorRepository vendorRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/vendor/all")
	public List<Vendor> getAllVendors() {
		return vendorRepository.findAll();
	}
	
	
	@GetMapping("/vendor/{vendorId}")
	public Vendor getVendorById(@PathVariable("vendorId") Long vendorId) {
		Optional<Vendor> vendor = vendorRepository.findById(vendorId);
		if(vendor.isPresent())
			return vendor.get();
		throw new RuntimeException("Vendor with the provided information does not exist...");
	}
		
	@PostMapping("/vendor")
	public void addVendor(@RequestBody Vendor vendor) {
		vendorRepository.save(vendor);
	}
	
	@PutMapping("/vendor/{vendorId}")
	public void updateVendor(@PathVariable("vendorId") Long vendorId, @RequestBody Vendor newVendor) {
		Optional<Vendor> optional = vendorRepository.findById(vendorId);
		if(optional.isPresent()) {
			Vendor existingVendor = optional.get();
			existingVendor.setVendorName(newVendor.getVendorName());
			existingVendor.setBalance(newVendor.getBalance());
			vendorRepository.save(existingVendor);
		}
		else
			throw new RuntimeException("ID is invalid");
	}
	
	@DeleteMapping("/vendor/{vendorId}")
	public void deleteVendor(@PathVariable("vendorId") Long vendorId) {
		vendorRepository.deleteById(vendorId);
		userRepository.deleteById(vendorId-1);
	}
	

	@GetMapping("/vendor/{vendorId}/inventory")
	public List<Product> getInventory(@PathVariable("vendorId") Long vendorId, 
			@RequestParam(name="filterBy", required=false) String filterBy, 
			@RequestParam(name="queryParam", required=false) String queryParam){
		List<Product> ret = new ArrayList<>();
		ret = vendorRepository.getInventory(vendorId);
		return (queryParam == null) ? 
				ret : ret.stream().filter(p -> p.getProductName().toLowerCase().contains(queryParam.toLowerCase())).collect(Collectors.toList());
	}


	@GetMapping("/vendor/{vendorId}/order_history")
	public List<Orders> getOrderHistory(@PathVariable("vendorId") Long vendorId){
		return vendorRepository.getOrderHistory(vendorId);
	}

	
}