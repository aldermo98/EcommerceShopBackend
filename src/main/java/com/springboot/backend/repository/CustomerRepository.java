package com.springboot.backend.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.springboot.backend.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{

	@Transactional
	@Modifying
	@Query("UPDATE Customer c SET c.balance = (c.balance + ?2) WHERE c.id=?1")
	void updateBalance(Long id, double price);
	
}
