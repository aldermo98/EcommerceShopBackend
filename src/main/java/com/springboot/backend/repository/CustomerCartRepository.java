package com.springboot.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.backend.model.CustomerCart;

public interface CustomerCartRepository extends JpaRepository<CustomerCart, Long> {

}
