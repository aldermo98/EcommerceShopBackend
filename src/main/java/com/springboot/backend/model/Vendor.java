package com.springboot.backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
public class Vendor {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	private String vendorName; 
	
	@Column(nullable = true)
	private String password;
	
	@Column(nullable = false)
	private double balance;

	public Vendor() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Vendor(Long id, String vendorName, String password, Double balance) {
		super();
		this.id = id;
		this.vendorName = vendorName;
		this.password = password;
		this.balance = balance;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "Vendor [id=" + id + ", vendorName=" + vendorName + ", password=" + password + ", balance=" + balance
				+ "]";
	}
	
	

	
	

}
