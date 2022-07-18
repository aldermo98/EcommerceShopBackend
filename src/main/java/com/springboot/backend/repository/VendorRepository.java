package com.springboot.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.backend.model.Vendor;

public interface VendorRepository extends JpaRepository<Vendor, Long>{

}
