package com.springboot.backend.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.backend.model.Vendor;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.springboot.backend.model.Orders;
import com.springboot.backend.model.Product;
import com.springboot.backend.model.Vendor;

public interface VendorRepository extends JpaRepository<Vendor, Long>{
	
	@Query("select v from Vendor v where v.vendorName=?1")
	Vendor findByName(String vendorName);
	
	@Query("select v.balance from Vendor v where v.id=?1")
	Double getBalance(Long id);
	
	@Query("select p from Product p where p.vendorId=?1")
	List<Product> getInventory(Long vendorId);
	
	@Query("select o from Orders o where o.vendorId=?1")
	List<Orders> getOrderHistory(Long vendorId);
	
}
