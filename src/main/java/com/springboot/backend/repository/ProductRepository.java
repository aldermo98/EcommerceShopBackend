package com.springboot.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springboot.backend.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	
	@Query(" select p from Product p where p.category.id = ?1")
	List<Product> getProductsbyCategoryId(Long cid);

	//@Query("select p  from Product p where p.vendorId = ?1")
	//List<Product> getProductsbyVendorId(Long vid);
}
