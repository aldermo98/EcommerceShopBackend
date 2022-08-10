package com.springboot.backend.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
public class Orders {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = true)
	private Long vendorId;
	
	@Column(nullable = false)
	private Long customerId;
		
	@Column(nullable = false)
	private String productName;
	
	@Column(nullable = false)
	private double price;
	
	@Column(nullable = false)
	private Integer quantity;
	
	@Column(nullable = false)
	private Boolean isApproved;
	
	@Column(nullable = false)
	private LocalDate purchaseDate;

	public Orders() {
		super();
	}

	public Orders(Long id, Long vendorId, Long customerId, String productName, Double price, Integer quantity,
			Boolean isApproved, LocalDate purchaseDate) {
		super();
		this.id = id;
		this.vendorId = vendorId;
		this.customerId = customerId;
		this.productName = productName;
		this.price = price;
		this.quantity = quantity;
		this.isApproved = isApproved;
		this.purchaseDate = purchaseDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getVendorId() {
		return vendorId;
	}

	public void setVendorId(Long vendorId) {
		this.vendorId = vendorId;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Boolean getIsApproved() {
		return isApproved;
	}

	public void setIsApproved(Boolean isApproved) {
		this.isApproved = isApproved;
	}

	public LocalDate getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(LocalDate purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	@Override
	public String toString() {
		return "Orders [id=" + id + ", vendorId=" + vendorId + ", customerId=" + customerId + ", productName="
				+ productName + ", price=" + price + ", quantity=" + quantity + ", isApproved=" + isApproved
				+ ", purchaseDate=" + purchaseDate + "]";
	}
}
