package com.springboot.backend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.backend.model.Admin;
import com.springboot.backend.repository.AdminRepository;

@RestController
public class AdminController {
	@Autowired
	private AdminRepository adminRepository;
	
	@GetMapping("/admin/all")
	public List<Admin> getAllAdmins() {
		return adminRepository.findAll();
	}
	
	@GetMapping("/admin/{adminId}")
	public Admin getVendorById(@PathVariable("adminId") Long adminId) {
		Optional<Admin> admin = adminRepository.findById(adminId);
		if(admin.isPresent())
			return admin.get();
		throw new RuntimeException("Admin with the provided information does not exist...");
	}
		
	@PostMapping("/admin")
	public void addVendor(@RequestBody Admin admin) {
		adminRepository.save(admin);
	}
	
	@PutMapping("/admin/{adminId}")
	public void updateAdmin(@PathVariable("adminId") Long adminId, @RequestBody Admin newAdmin) {
		Optional<Admin> optional = adminRepository.findById(adminId);
		if(optional.isPresent()) {
			Admin existingAdmin = optional.get();
			existingAdmin.setName(newAdmin.getName());
			adminRepository.save(existingAdmin);
		}
		else	
			throw new RuntimeException("ID is invalid");
	}
	
	@DeleteMapping("/admin/{adminId}")
	public void deleteAdmin(@PathVariable("adminId") Long adminId) {
		adminRepository.deleteById(adminId);
	}

}