package com.springboot.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springboot.backend.model.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long>{
	
	@Query("select u from Admin u where u.username =?1 ")
	Admin getbyUsername(String username);

}
