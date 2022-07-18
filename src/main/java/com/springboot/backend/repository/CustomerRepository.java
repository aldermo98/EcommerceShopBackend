package com.springboot.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.backend.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{

}
