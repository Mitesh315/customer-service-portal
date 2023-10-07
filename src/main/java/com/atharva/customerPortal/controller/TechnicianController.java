package com.atharva.customerPortal.controller;

import java.util.List;
import java.util.Optional;

import com.atharva.customerPortal.model.Technician;
import com.atharva.customerPortal.dao.TechnicianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
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

import com.atharva.customerPortal.mailService.MailService;


@RestController
@RequestMapping("/api/v1")
public class TechnicianController {
	
	  @Autowired
	  private TechnicianRepository technicianRepository;
	  
	  @Autowired
	  private PasswordEncoder passwordEncoder;
	  
	  @Autowired
	  private MailService mailService;

	  
	  /**
	   * Create technician.
	   */
	  @PostMapping("/create-technician")
	  public ResponseEntity<Technician> createUser(@RequestBody Technician technician) {
		  System.out.println("Technician data recived from technician=>>>>>"+technician.toString());
		  
		  ResponseEntity<Technician> responseEntity = null;
		  Technician existTechnician = technicianRepository.findByTechnicianLoginId(technician.getTechnicianLoginId());
		  
		  if(existTechnician != null) {
			responseEntity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);  
		  }else {
			  String encryptedPassword = passwordEncoder.encode(technician.getPassword());
			  technician.setPassword(encryptedPassword);
			  technician = technicianRepository.save(technician);
			  technician.setPassword(null);
			responseEntity = new ResponseEntity<Technician>(technician, HttpStatus.OK);	  
		  }    
		  return responseEntity;
	  }

	  @GetMapping("/technicians")
	  public  ResponseEntity<List<Technician>>  getAllTechnicians() {
		    HttpHeaders responseHeaders = new HttpHeaders();
		    responseHeaders.set("Access-Control-Allow-Origin","*");

		    return ResponseEntity.ok()
		      .headers(responseHeaders)
		      .body(technicianRepository.findAll());		  
	  }
	  
		@GetMapping("/technicians/{id}")
		public Technician getTechnicianById(@PathVariable(value = "id") Long technicianId)
				throws ConfigDataResourceNotFoundException {
			Optional<Technician> technician = technicianRepository.findById(technicianId);

			if (technician.isPresent()) {
				return technician.get();
			} else {
				System.out.println("Customer Not Found customerId" + technicianId);
				return new Technician();
			}
		}

	  
	  
	 

	  /**
	   * Update technician response entity.
	   *
	   */
	  @PutMapping("/technicians/{id}")
	  public ResponseEntity<Technician> updateTechnician(
	      @PathVariable(value = "id") Long technicianId, 
	      @RequestBody Technician technicianDetails) {

	    Optional<Technician> technicianOptional = technicianRepository.findById(technicianId);
	    
	    if(!technicianOptional.isPresent()) {
	    	return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	    
	    Technician technician = technicianOptional.get();
	    
	    technician.setTechnicianName(technicianDetails.getTechnicianName());
	    technician.setTechnicianLoginId(technicianDetails.getTechnicianLoginId());
	    
	    if(technicianDetails.getPassword() != null || !technicianDetails.getPassword().isEmpty())
	    technician.setPassword(technicianDetails.getPassword());
    
	    final Technician updatedTechnician = technicianRepository.save(technician);
	    return new ResponseEntity<Technician>(updatedTechnician, HttpStatus.OK);
	  }
	  
		/**
		 * Login technician.
		 *
		 */
		@PostMapping("/tech/validate-login")
		public ResponseEntity<Technician> validateLoginDetail(@RequestBody Technician technician) {

			System.out.println("validate-login request Body >>>>>>> "+ technician.toString());
			
			if (StringUtils.isEmpty(technician.getTechnicianLoginId()) || StringUtils.isEmpty(technician.getPassword())) {
				throw new RuntimeException("Technician Login-ID or Password Invalid");
			}

			Technician technicianEntity = technicianRepository.findByTechnicianLoginId(technician.getTechnicianLoginId());

			if (technicianEntity == null) {
				throw new RuntimeException("Technician Login ID Doesn't exist");
			}

			if (passwordEncoder.matches(technician.getPassword(),technicianEntity.getPassword())) {
				HttpHeaders httpHeaders = new HttpHeaders();
				httpHeaders.set("Access-Control-Allow-Origin", "*");
				//httpHeaders.set("Access-Control-Allow-Headers","Origin");
				//httpHeaders.set("Access-Control-Allow-Headers","Content-Type");				
				return ResponseEntity.ok().headers(httpHeaders).body(technician);
			} else {
				throw new RuntimeException("Password Invalid");
			}
		}
}
		  
	  
		
		
	
