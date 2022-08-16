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

	@GetMapping("/customer/cart/{cid}")
	public List<CustomerCart> showCart(@PathVariable("cid") Long cid) {
		
		Optional<Customer> optional = customerRepository.findById(cid);
		if (optional.isPresent()) {
			return customerCartRepository.fetchCartByCustomerId(cid);
		} else
			throw new RuntimeException("Invalid customer id");
	}

	@PostMapping("/customer/cart/{cid}/{pid}")
	public CustomerCart addProductIntoCart(@PathVariable("cid") Long cid, @PathVariable("pid") Long pid) {

		CustomerCart customerCart = new CustomerCart();
		Optional<Customer> optionalC = customerRepository.findById(cid);
		if (optionalC.isPresent()) {
			Optional<Product> optionalP = productRepository.findById(pid);
			if(optionalP.isPresent()) {
				Optional<CustomerCart> optionalCC = customerCartRepository.getExistingRecord(cid, pid);
				if(optionalCC.isPresent()) {
					customerCart = optionalCC.get();
					customerCart.setQuantity(customerCart.getQuantity() + 1);
					customerCart.setTotalPrice(customerCart.getProduct().getPrice() * customerCart.getQuantity());
				}else {
					customerCart.setCustomer(optionalC.get());
					customerCart.setProduct(optionalP.get());
					customerCart.setQuantity(1);
					customerCart.setTotalPrice(customerCart.getProduct().getPrice());
				}
			}else
				throw new RuntimeException("Invalid Product ID");
		}else
			throw new RuntimeException("Invalid Customer ID");
		return customerCartRepository.save(customerCart);
			
	}

	@DeleteMapping("/customer/cart/delete/{cid}/{pid}")
	public CustomerCart removeProductFromCart(@PathVariable("cid") Long cid, @PathVariable("pid") Long pid) {

		Optional<Product> optional = productRepository.findById(pid);
		if (optional.isPresent()) {
			Product product = optional.get();
			Optional<CustomerCart> optionalC = customerCartRepository.getExistingRecord(cid, pid);
			if(optionalC.isPresent()) {
				customerCartRepository.delete(optionalC.get());
				return optionalC.get();
			}else
				throw new RuntimeException("Invalid Customer ID");
		} else
			throw new RuntimeException("Invalid product id");
	}

}
