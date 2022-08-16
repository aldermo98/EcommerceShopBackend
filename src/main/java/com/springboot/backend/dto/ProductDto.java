package com.springboot.backend.dto;

public class ProductDto {
	private Long id;
	private String name;
	private Double price;
	private String category;
	private Integer quantity;
	private Integer vendor;
	public Integer getVendor() {
		return vendor;
	}
	public void setVendor(Integer vendor) {
		this.vendor = vendor;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getCategory() {
		return category;
	}
	public void setCname(String cname) {
		this.category = cname;
	}
	
	
}
