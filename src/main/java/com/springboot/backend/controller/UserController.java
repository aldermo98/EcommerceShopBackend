package com.springboot.backend.controller;

import java.security.Principal;
import java.time.LocalDate;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.backend.dto.UserDto;
import com.springboot.backend.dto.UserInfoDto;
import com.springboot.backend.model.UserInfo;
import com.springboot.backend.repository.UserRepository;
@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
public class UserController {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder; 

	@PostMapping("/user")
	public void postUser(@RequestBody UserDto userDto) {
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

	}

	@GetMapping("/login") //username/password
	public UserInfoDto login(Principal principal) {
		String username = principal.getName();
		UserInfo info = userRepository.getbyUsername(username);
		UserInfoDto dto = new UserInfoDto();
		dto.setId(info.getId());
		dto.setName(info.getName());
		dto.setUsername(info.getUsername());
		dto.setRole(info.getRole());
		return dto; 
	}
}