package com.shri.springdemo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shri.springdemo.entity.Customer;
import com.shri.springdemo.service.CustomerService;

@RestController
@RequestMapping("/api")
public class CustomerRestControler {

	// autowire the CustomerService
	@Autowired
	private CustomerService customerService;
	
	// add mapping for GET /customers
	@GetMapping(path = "/customers")
	public List<Customer> getCustomers() {
		return customerService.getCustomers();
	}
	
	// add mapping for GET /customers/{customerId}
	@GetMapping("/customers/{customerId}")
	public Customer getCustomer(@PathVariable int customerId) {
		Customer theCustomer = customerService.getCustomer(customerId);
		System.out.println(theCustomer);
		
		if(theCustomer == null)
			throw new CustomerNotFoundException("Customer id not found - " + customerId);
		
		return theCustomer;
	}
	
	// add mapping for POST /customers - add new customer
	@PostMapping("/customers")
	/* @RequestBody will allows us to access the request body as a POJO by binding this method
	 * parameter with the POJO.
	 * Note: Jackson will automatically convert the json data in the body into POJO and for this
	 * to happen the REST client should send the content-type as application/json in the header.
	 */
	public Customer addCustomer(@RequestBody Customer theCustomer) {
		
		// We are setting the id to 0 so as to force a save of new item.
		theCustomer.setId(0);
		
		customerService.saveCustomer(theCustomer);
		
		return theCustomer;
		
	}
	
	// add mapping for PUT /customers - update existing customer
	@PutMapping("/customers")
	public Customer updateCustomer(@RequestBody Customer theCustomer) {
		customerService.saveCustomer(theCustomer);
		
		return theCustomer;
	}
	
	// add mapping for DELETE /customers/{customerId} - delete customer
	@DeleteMapping("/customers/{customerId}")
	public String deleteCustomer(@PathVariable int customerId) {
		
		Customer customer = customerService.getCustomer(customerId);
		
		// throw exception if customer is null
		if(customer == null)
			throw new CustomerNotFoundException("Customer id not found - " + customerId);
				
		
		customerService.deleteCustomer(customerId);
		
		return "Deleted cutomer id - " + customerId;
	}
	
}
