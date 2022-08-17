package com.springboot.backend.controller;

import java.security.Principal;
import java.time.LocalDate;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.backend.dto.UserDto;
import com.springboot.backend.dto.UserInfoDto;
import com.springboot.backend.model.Admin;
import com.springboot.backend.model.Customer;
import com.springboot.backend.model.UserInfo;
import com.springboot.backend.model.Vendor;
import com.springboot.backend.repository.UserRepository;
import com.springboot.backend.repository.CustomerRepository;
import com.springboot.backend.repository.VendorRepository;

@RestController
@CrossOrigin(origins = { "http://localhost:4200/" }) // changes based on angular port
public class UserController {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private VendorRepository vendorRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostMapping("/user")
	public void postUser(@RequestBody UserDto userDto, @RequestParam("balance")Double bal) {
		String str = new String(Base64.getDecoder().decode(userDto.getEncodedCredentials()));
		String username = str.split("@%")[0];
		String password = str.split("@%")[1];

		UserInfo info = new UserInfo();
		info.setName(userDto.getName());
		info.setPassword(passwordEncoder.encode(password));
		info.setUsername(username);
		info.setPasswordLastReset(LocalDate.now());
		info.setSecurityQuestion(userDto.getSecurityQuestion());
		info.setSecurityAnswer(userDto.getSecurityAnswer());
		info.setRole(userDto.getRole());
		userRepository.save(info);

		if (info.getRole().equalsIgnoreCase("customer")) {
			Customer customer = new Customer();
			customer.setName(info.getName());
			customer.setPassword(info.getPassword());
			customer.setUsername(info.getUsername());
			customer.setBalance(bal);
			customer.setPasswordLastReset(info.getPasswordLastReset());
			customer.setSecurityQuestion(info.getSecurityQuestion());
			customer.setSecurityAnswer(info.getSecurityAnswer());
			customer = customerRepository.save(customer);
			info.setUserId(customer.getId());
			userRepository.save(info);
		}

		if (info.getRole().equalsIgnoreCase("vendor")) {
			Vendor vendor = new Vendor();
			vendor.setVendorName(info.getName());
			vendor.setPassword(info.getPassword());
			vendor.setVendorName(info.getUsername());
			vendor.setBalance(bal);
			vendor.setPasswordLastReset(info.getPasswordLastReset());
			vendor.setSecurityQuestion(info.getSecurityQuestion());
			vendor.setSecurityAnswer(info.getSecurityAnswer());
			vendor = vendorRepository.save(vendor);
			info.setUserId(vendor.getId());
			userRepository.save(info);
		}

	}

	@GetMapping("/login") // username/password
	public UserInfoDto login(Principal principal) {
		System.out.println(principal);
		String username = principal.getName();
		UserInfo info = userRepository.getbyUsername(username);
		UserInfoDto dto = new UserInfoDto();
		dto.setId(info.getId());
		dto.setUserId(info.getUserId());
		dto.setName(info.getName());
		dto.setUsername(info.getUsername());
		dto.setRole(info.getRole());
		return dto;
	}
	
	@GetMapping("/user/all")
	public List<UserInfo> getAllUsers() {
		return userRepository.findAll();
	}
	
	@DeleteMapping("/user/{userId}")
	public void deleteUser(@PathVariable("userId") Long userId) {
		userRepository.deleteById(userId);
	}
}