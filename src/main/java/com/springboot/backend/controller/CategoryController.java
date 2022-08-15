package com.springboot.backend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.backend.model.Category;
import com.springboot.backend.repository.CategoryRepository;

@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
public class CategoryController {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	// API  to add category
	@PostMapping("/category")
	public void PostCategory(@RequestBody Category category) {
		categoryRepository.save(category);
	}
	
	//API--> Get all categories 
	@GetMapping("/category")
	public List<Category> getAllCategories(){
		return categoryRepository.findAll();
	}
	
	// API to delete by category
	@DeleteMapping("/category/{id}")
	public void deleteCategory(@PathVariable("id") Long id) {
		categoryRepository.deleteById(id);
	}
	
	
	// API to edit existing category
	@PutMapping("/category/{id}")
	public Category updateCategory(@PathVariable("id") Long id,
			@RequestBody Category newCategory) {
		Optional<Category> optional =categoryRepository.findById(id);
		if(optional.isPresent()) {
			Category existingCategory =  optional.get();
			existingCategory.setName(newCategory.getName());
			return categoryRepository.save(existingCategory);
		}
		else	
			throw new RuntimeException("ID is invalid");
	}
	
	
	
	

}
