package com.springboot.backend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.backend.model.Category;
import com.springboot.backend.repository.CategoryRepository;

@RestController
public class CategoryController {

	@Autowired //<- Spring will wire it to CategoryRepository interface. 
	private CategoryRepository categoryRepository; 
	
	@GetMapping("/hello")    //REST API: Representational State Transfer 
	public String sayHello() {
		return "Hello All!!! welcome to spring!";
	}
	
	@PostMapping("/category")
	public void postCategory(@RequestBody Category category) {
		//we use JpaRepository Interface 
		categoryRepository.save(category);
	}
	
	@GetMapping("/category")
	public List<Category> getAllCategories() {
		List<Category> list = categoryRepository.findAll();
		return list; 
	}
	
	@GetMapping("/category/single/{id}") //category/single/4
	public Category getSingleCategoryById(@PathVariable("id") Long id) {
		Optional<Category> optional =  categoryRepository.findById(id);
		if(optional.isPresent())
			return optional.get();
		throw new RuntimeException("ID is invalid");
		  
	}
}
