package com.springboot.backend.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Purchases {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private int customerId;
	
	private int customerCartId;
	
	private double totalPurchase;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public int getCustomerCartId() {
		return customerCartId;
	}

	public void setCustomerCartId(int customerCartId) {
		this.customerCartId = customerCartId;
	}

	public double getTotalPurchase() {
		return totalPurchase;
	}

	public void setTotalPurchase(double totalPurchase) {
		this.totalPurchase = totalPurchase;
	}
	
	

}
