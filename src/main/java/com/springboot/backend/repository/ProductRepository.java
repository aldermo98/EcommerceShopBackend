package com.springboot.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.backend.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
