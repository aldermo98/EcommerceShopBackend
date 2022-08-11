package com.springboot.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springboot.backend.model.CustomerCart;

public interface CustomerCartRepository extends JpaRepository<CustomerCart, Long> {

	@Query("select c from CustomerCart c where c.customer.id=?1") 
	CustomerCart findByCustomerId(Long cid);

}
