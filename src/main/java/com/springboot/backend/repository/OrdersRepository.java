package com.springboot.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springboot.backend.model.Orders;

public interface OrdersRepository extends JpaRepository<Orders, Long>{

	@Query("select o from Orders o where o.customer.id=?1")
	List<Orders> getOrdersByCustomer(Long customerId);
	
	@Query("select o from Orders o where o.product.vendorId=?1")
	List<Orders> getOrdersByVendor(Long vendorId);
	
}
