package com.springboot.backend.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.backend.dto.ProductDto;
import com.springboot.backend.model.Category;
import com.springboot.backend.model.Product;
import com.springboot.backend.model.Vendor;
import com.springboot.backend.repository.CategoryRepository;
import com.springboot.backend.repository.ProductRepository;
import com.springboot.backend.repository.VendorRepository;

@CrossOrigin(origins = {"http://localhost:58910"})
@RestController
public class ProductController {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private VendorRepository vendorRepository;
	
	
	
	/* Task: Get all the products*/
	/* Scenario: Browsing all the products of website */
	/*
	 * NOTE 
	 * Do not use this API
	 * THIS API displays all the info including passwords of vendor 
	 * 
	 */
	@GetMapping("/productsALL")
	public List<Product> getAllProduct(
			@RequestParam(name="page", required= false, defaultValue ="0") Integer page,
			@RequestParam(name="size", required=false, defaultValue ="100") Integer size)
		{
			if(page < 0)
				page=0;
			Pageable pageable = PageRequest.of(page, size);
			
			Page<Product> p = productRepository.findAll(pageable);
			long total = p.getTotalElements();
			
			return p.getContent();
			
		
		}
	
	@GetMapping("/products")
	public List<ProductDto> getAllProducts() {
		List<Product> list = productRepository.findAll();
		List<ProductDto> listDto =  new ArrayList<>();
		list.stream().forEach(p->{
			ProductDto dto = new ProductDto();
			dto.setId(p.getId());
			dto.setName(p.getProductName());
			dto.setPrice(p.getPrice());
			dto.setQuantity(p.getQuantity());
			dto.setCname(p.getCategory().getName());
			dto.setVname(p.getVendor().getVendorName());
			listDto.add(dto);
		});
		return listDto;
	}
	
	/* Task: Add the products*/
	@PostMapping("/product/{cid}/{vid}")
	public Product PostProduct(@RequestBody Product product,
			@PathVariable("cid") Long cid,
			@PathVariable("vid") Long vid) {
		
		/*  Go to repo and fetch category by id*/
		Optional<Category> optional = categoryRepository.findById(cid);
		if(!optional.isPresent())
			throw new RuntimeException("Category ID is Invalid!!");
		Category category = optional.get();
		
		/* go to repo and fetch vendor by vid*/
		Optional<Vendor> optionalV = vendorRepository.findById(vid);
		
		if(!optional.isPresent())
			throw new RuntimeException("Vendor ID is Invalid!!");
		
		Vendor vendor = optionalV.get();
		
		/*  Attach category and vendor to the product*/
		product.setCategory(category);
		product.setVendor(vendor);
		
		/* Save the product in DB*/
		
		return productRepository.save(product);
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
