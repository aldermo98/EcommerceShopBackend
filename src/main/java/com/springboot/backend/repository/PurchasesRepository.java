package com.springboot.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.backend.model.Purchases;

public interface PurchasesRepository extends JpaRepository<Purchases, Long>{

}
