package com.springboot.backend.dto;

public class UserDto {
	private String name;
	private String role;
	private String securityAnswer;
	private String securityQuestion;
	private String encodedCredentials;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getSecurityAnswer() {
		return securityAnswer;
	}

	public void setSecurityAnswer(String securityAnswer) {
		this.securityAnswer = securityAnswer;
	}

	public String getSecurityQuestion() {
		return securityQuestion;
	}

	public void setSecurityQuestion(String securityQuestion) {
		this.securityQuestion = securityQuestion;
	}

	public String getEncodedCredentials() {
		return encodedCredentials;
	}

	public void setEncodedCredentials(String encodedCredentials) {
		this.encodedCredentials = encodedCredentials;
	}

}