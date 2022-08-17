package com.springboot.backend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.backend.model.Admin;
import com.springboot.backend.model.UserInfo;
import com.springboot.backend.repository.AdminRepository;
import com.springboot.backend.repository.UserRepository;

@RestController
public class AdminController {
	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
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
		
		UserInfo info = new UserInfo();
		info.setName(admin.getName());
		info.setPassword(passwordEncoder.encode(admin.getPassword()));
		info.setUsername(admin.getUsername());
		info.setPasswordLastReset(admin.getPasswordLastReset());
		info.setSecurityQuestion(admin.getSecurityQuestion());
		info.setSecurityAnswer(admin.getSecurityAnswer());		
		info.setUserId(admin.getId());
		info.setRole("admin");
		userRepository.save(info);
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