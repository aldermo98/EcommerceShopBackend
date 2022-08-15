package com.springboot.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springboot.backend.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{
	
	@Query("select c from Category c where c.name=?1")
	Category findByName(String name);
}
