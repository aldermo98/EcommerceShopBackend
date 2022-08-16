package com.springboot.backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springboot.backend.model.CustomerCart;

public interface CustomerCartRepository extends JpaRepository<CustomerCart, Long> {

	@Query("select c from CustomerCart c where c.customer.id=?1 and c.product.id=?2")
	Optional<CustomerCart> getExistingRecord(Long cid, Long pid);
	
	@Query("select c from CustomerCart c where c.customer.id=?1")
	List<CustomerCart> fetchCartByCustomerId(Long cid);

}
