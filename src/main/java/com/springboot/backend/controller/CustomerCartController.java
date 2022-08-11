package com.springboot.backend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.backend.model.Customer;
import com.springboot.backend.model.CustomerCart;
import com.springboot.backend.model.Product;
import com.springboot.backend.repository.CustomerCartRepository;
import com.springboot.backend.repository.CustomerRepository;
import com.springboot.backend.repository.ProductRepository;

@RestController
public class CustomerCartController {

	@Autowired
	private CustomerCartRepository customerCartRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private ProductRepository productRepository;

	@PostMapping("/customer/{cid}/cart")
	public void createCustomerCart(@PathVariable("cid") Long cid, @RequestBody CustomerCart customerCart) {
		
		Optional<Customer> optional = customerRepository.findById(cid);
		if (optional.isPresent()) {
			customerCart.setCustomer(optional.get());
			customerCart.setTotalPrice(0);
			customerCartRepository.save(customerCart);
		} else
			throw new RuntimeException("Invalid customer id");
	}

	@GetMapping("/customer/{cid}/cart")
	public CustomerCart showCart(@PathVariable("cid") Long cid) {
		
		Optional<Customer> optional = customerRepository.findById(cid);
		if (optional.isPresent()) {
			return customerCartRepository.findByCustomerId(cid);
		} else
			throw new RuntimeException("Invalid customer id");
	}

	@PutMapping("/customer/{cid}/cart/{pid}")
	public void addProductIntoCart(@PathVariable("cid") Long cid, @PathVariable("pid") Long pid) {

		Optional<Product> optional = productRepository.findById(pid);
		if (optional.isPresent()) {
			Product product = optional.get();
			CustomerCart cart = customerCartRepository.findByCustomerId(cid);
			List<Product> productList = cart.getProducts();
			productList.add(product);
			cart.setProducts(productList);
			cart.setTotalPrice(cart.getTotalPrice() + product.getPrice());
			customerCartRepository.save(cart);
		} else
			throw new RuntimeException("Invalid product id");
	}

	@PutMapping("/customer/{cid}/cart/delete/{pid}")
	public void removeProductFromCart(@PathVariable("cid") Long cid, @PathVariable("pid") Long pid) {

		Optional<Product> optional = productRepository.findById(pid);
		if (optional.isPresent()) {
			Product product = optional.get();
			CustomerCart cart = customerCartRepository.findByCustomerId(cid);
			List<Product> productList = cart.getProducts();
			productList.remove(product);
			cart.setProducts(productList);
			cart.setTotalPrice(cart.getTotalPrice() - product.getPrice());
			customerCartRepository.save(cart);
		} else
			throw new RuntimeException("Invalid product id");
	}

	@PutMapping("/customer/{cid}/cart/deleteAll")
	public void removeAllProductsFromCart(@PathVariable("cid") Long cid) {

		CustomerCart cart = customerCartRepository.findByCustomerId(cid);
		List<Product> productList = null;
		cart.setProducts(productList);
		cart.setTotalPrice(0);
		customerCartRepository.save(cart);
	}

}
