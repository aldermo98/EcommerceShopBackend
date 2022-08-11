package com.springboot.backend.dto;

public class ProductDto {
	private Long id;
	private String name;
	private Double price;
	private String cname;
	private String vname;
	private Integer quanity;
	public Integer getQuanity() {
		return quanity;
	}
	public void setQuantity(Integer quanity) {
		this.quanity = quanity;
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
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getVname() {
		return vname;
	}
	public void setVname(String string) {
		this.vname = string;
	}
	
}
