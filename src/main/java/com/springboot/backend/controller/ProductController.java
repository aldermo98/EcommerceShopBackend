package com.springboot.backend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.backend.model.Product;
import com.springboot.backend.repository.ProductRepository;

@RestController
public class ProductController {
	
	@Autowired
	private ProductRepository productRepository;
	
	
	
	/* Task: Get all the products*/
	/* Scenario: Browsing all the products of website */
	@GetMapping("/products")
	public List<Product> getAllProduct(){
		List <Product> list = productRepository.findAll();
		return list;
	}
	
	/* Task: Add the products*/
	@PostMapping("/products")
	public void postProduct(@RequestBody Product product ) {
		productRepository.save(product);
	}
	
	/* Update the products */
	@PutMapping("products/{id}")
	public Product updateProduct(@PathVariable("id") Long id, 
			@RequestBody Product newProduct) {
		Optional<Product> optional = productRepository.findById(id);
		if(optional.isPresent()) {
			Product existingProduct = optional.get();
			existingProduct.setProductName(newProduct.getProductName());
			existingProduct.setQuantity(newProduct.getQuantity());
			existingProduct.setPrice(newProduct.getPrice());
			return productRepository.save(existingProduct);
		}
		else
			throw new RuntimeException("ID is Invalid!!!");
	}
	/* Delete the products */
	@DeleteMapping("/products/{id}")
	public void deleteProduct(@PathVariable("id") Long id) {
		productRepository.deleteById(id);;
	}
	
	
	
	
	

}
