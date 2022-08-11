package com.springboot.backend.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class UserInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String securityQuestion; 

	@Column(nullable = false)
	private String securityAnswer; 

	private LocalDate passwordLastReset; 

	public UserInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserInfo(long id, String name, String username, String password, String role) {
		super();
		this.id = id;
		this.name = name;
		this.username = username;
		this.password = password;
		this.role = role;
	}

	@Override
	public String toString() {
		return "UserInfo [id=" + id + ", name=" + name + ", username=" + username + ", password=" + password + ", role="
				+ role + "]";
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	

	public String getSecurityQuestion() {
		return securityQuestion;
	}

	public void setSecurityQuestion(String securityQuestion) {
		this.securityQuestion = securityQuestion;
	}

	public String getSecurityAnswer() {
		return securityAnswer;
	}

	public void setSecurityAnswer(String securityAnswer) {
		this.securityAnswer = securityAnswer;
	}

	public LocalDate getPasswordLastReset() {
		return passwordLastReset;
	}

	public void setPasswordLastReset(LocalDate passwordLastReset) {
		this.passwordLastReset = passwordLastReset;
	}

	@Column(nullable = false)
	private String username;

	@Column(nullable = false)
	private String password;

	@Column(nullable = false)
	private String role;
}
