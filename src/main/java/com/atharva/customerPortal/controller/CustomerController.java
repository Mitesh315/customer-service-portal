 package com.atharva.customerPortal.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import java.util.Optional;

import com.atharva.customerPortal.model.Customer;
import com.atharva.customerPortal.dao.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atharva.customerPortal.mailService.Mail;
import com.atharva.customerPortal.mailService.MailService;


@RestController
@RequestMapping("/api/v1")
public class CustomerController {
	
	  @Autowired
	  private CustomerRepository customerRepository;
	  
	  @Autowired
	  private PasswordEncoder passwordEncoder;
	  
	  @Autowired
	  private MailService mailService;

	  @Autowired
	  private ResourceLoader resourceLoader;

	  @GetMapping("/customers")
	  public  ResponseEntity<List<Customer>>  getAllCustomers() {
		    HttpHeaders responseHeaders = new HttpHeaders();
		    responseHeaders.set("Access-Control-Allow-Origin","*");

		    return ResponseEntity.ok()
		      .headers(responseHeaders)
		      .body(customerRepository.findAll());		  
	  }
	  
		@GetMapping("/customer/{id}")
		public Customer getCustomerById(@PathVariable(value = "id") Long customerId)
				throws ConfigDataResourceNotFoundException {
			Optional<Customer> customer = customerRepository.findById(customerId);

			if (customer.isPresent()) {
				return customer.get();
			} else {
				System.out.println("Customer Not Found customerId" + customerId);
				return new Customer();
			}
		}

	  /**
	   * Create customer.
	   */
	  @PostMapping("/customer")
	  public ResponseEntity<Customer> createUser(@RequestBody Customer customer) {
		  System.out.println("Customer data received from Client =>>>>>"+customer.toString());
		  
		  ResponseEntity<Customer> responseEntity = null;
		  Customer existingCustomer = customerRepository.findByEmail(customer.getEmail());
		  System.out.println(existingCustomer);
		  
		  if(existingCustomer != null) {
			responseEntity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);  
		  }else {
			  String encryptedPassword = passwordEncoder.encode(customer.getPassword());
			  customer.setPassword(encryptedPassword);
			  customer = customerRepository.save(customer);
			  customer.setPassword(null);
			  responseEntity = new ResponseEntity<Customer>(customer, HttpStatus.OK);
//			sendSignupEmailToUser(customer);
		  }
		  return responseEntity;
	  }
	  
	 

	  /**
	   * Update customer response entity.
	   *
	   */
	  @PutMapping("/customers/{id}")
	  public ResponseEntity<Customer> updateCustomer(
	      @PathVariable(value = "id") Long customerId, 
	      @RequestBody Customer customerDetails) {

	    Optional<Customer> customerOptional = customerRepository.findById(customerId);
	    
	    if(!customerOptional.isPresent()) {
	    	return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	    
	    Customer customer = customerOptional.get();
	    
	    customer.setCustomerName(customerDetails.getCustomerName());
	    customer.setEmail(customerDetails.getEmail());
	    
	    if(customerDetails.getPassword() != null || !customerDetails.getPassword().isEmpty())
	    customer.setPassword(customerDetails.getPassword());
    
	    final Customer updatedCustomer = customerRepository.save(customer);
	    return new ResponseEntity<Customer>(updatedCustomer, HttpStatus.OK);
	  }
	  
		/**
		 * Login customer.
		 *
		 */
		@PostMapping("/validate-login")
		public ResponseEntity<Customer> validateLoginDetail(@RequestBody Customer customer) {

			System.out.println("validate-login request Body >>>>>>> "+ customer.toString());
			
			if (StringUtils.isEmpty(customer.getEmail()) || StringUtils.isEmpty(customer.getPassword())) {
				throw new RuntimeException("E-mail id or Password Invalid");
			}

			Customer customerEntity = customerRepository.findByEmail(customer.getEmail());

			if (customerEntity == null) {
				throw new RuntimeException("E-mail id Doesn't exist");
			}

			if (passwordEncoder.matches(customer.getPassword(),customerEntity.getPassword())) {
				HttpHeaders httpHeaders = new HttpHeaders();
				httpHeaders.set("Access-Control-Allow-Origin", "*");
				//httpHeaders.set("Access-Control-Allow-Headers","Origin");
				//httpHeaders.set("Access-Control-Allow-Headers","Content-Type");
				
				return ResponseEntity.ok().headers(httpHeaders).body(customer);
			} else {
				throw new RuntimeException("Password Invalid");
			}
		}	  
		  
	  
		private void sendSignupEmailToUser(Customer customerEntity) {
			Mail mail = new Mail();
	        mail.setMailFrom("miteshjet2002@gmail.com");
			mail.setMailTo(customerEntity.getEmail());
	        mail.setMailSubject("Customer Service Portal");
	        mail.setMailContent("Welcome " + customerEntity.getCustomerName()+", You have created your account successfully.");
	        
	        mailService.sendEmail(mail, null);
		}
		
		
		
		
		
//
//	  /**
//	   * Delete user map.
//	   *
//	   * @param userId the user id
//	   * @return the map
//	   * @throws Exception the exception
//	   */
//	  @DeleteMapping("/user/{id}")
//	  public Map<String, Boolean> deleteUser(@PathVariable(value = "id") Long userId) throws Exception {
//	    User user =
//	        userRepository
//	            .findById(userId)
//	            .orElseThrow(() -> new ResourceNotFoundException("User not found on :: " + userId));
//
//	    userRepository.delete(user);
//	    Map<String, Boolean> response = new HashMap<>();
//	    response.put("deleted", Boolean.TRUE);
//	    return response;
//	  }
//	}
//	

	}
