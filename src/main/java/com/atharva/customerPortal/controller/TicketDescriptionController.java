package com.atharva.customerPortal.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;

import com.atharva.customerPortal.*;
import com.atharva.customerPortal.dao.CustomerRepository;
import com.atharva.customerPortal.dao.TicketDescriptionRepository;
import com.atharva.customerPortal.model.Customer;
import com.atharva.customerPortal.model.TicketDescription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atharva.customerPortal.mailService.Mail;
import com.atharva.customerPortal.mailService.MailService;



@RestController
@RequestMapping("/api/v1")
public class TicketDescriptionController {

	@Autowired
	TicketDescriptionRepository ticketDescriptionRepository;

	@Autowired
	CustomerRepository customerRepository;

	  @Autowired
	  private MailService mailService;

	
	/**
	 * API to get all tickets
	 */
	@GetMapping("/tickets")
	public List<TicketDescription> getAllTickets() {
		return ticketDescriptionRepository.findAll();
	}

	/**
	 * API for ticket by id
	 * 
	 * @param username
	 * @return
	 */
	@GetMapping("/ticket/{email}")
	public List<TicketDescription> getTicketById(@PathVariable(value = "email") String email) {
		Customer customer = customerRepository.findByEmail(email);
		List<TicketDescription> ticketDescriptionList = ticketDescriptionRepository.findByCustomerId(customer.getId());

		if (!ticketDescriptionList.isEmpty()) {
			return ticketDescriptionList;
		} else {
			return new ArrayList<>();
		}
	}

	/**
	 * Create ticket .
	 * @throws MessagingException 
	 */
	@PostMapping("/ticket")
	public ResponseEntity<TicketDescription> createTicket(@RequestBody TicketDescriptionDTO ticketDescriptionDTO) throws MessagingException {
		ResponseEntity<TicketDescription> responseEntity = null;

		System.out.println("TicketDescriptionDTO=  "+  ticketDescriptionDTO.toString());

		if (ticketDescriptionDTO == null || ticketDescriptionDTO.getEmail() == null) {
			System.out.println("TicketDescriptionDTO or CustomerEmail Can Not Null");
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} 
			TicketDescription entity = new TicketDescription();

			entity.setTicketDescription(ticketDescriptionDTO.getTicketDescription());
			entity.setTicketTitle(ticketDescriptionDTO.getTicketTitle());
			entity.setDevice(ticketDescriptionDTO.getDevice());
			
			Customer customer = customerRepository.findByEmail(ticketDescriptionDTO.getEmail());

			entity.setCustomer(customer);
			entity.setCreatedAt(new Timestamp(System.currentTimeMillis()));
			entity.setModifiedAt(new Timestamp(System.currentTimeMillis()));

			System.out.println("Printing Entity before save =  "+  entity.toString());						
			entity =  ticketDescriptionRepository.save(entity);
			
			sendTicketDescEmailToUser(customer, entity);

			responseEntity = new ResponseEntity<TicketDescription>(entity, HttpStatus.OK);
			return responseEntity;
	}

	@Async
	private void sendTicketDescEmailToUser(Customer customerEntity,TicketDescription ticketDescription) throws MessagingException {
		Mail mail = new Mail();
        mail.setMailFrom("miteshjet2002@gmail.com");
		mail.setMailTo(customerEntity.getEmail());
        mail.setMailSubject("Customer Service Portal - Ticket Raised");
        mail.setMailContent(" " + customerEntity.getCustomerName());
        
        
		MimeMultipart multipart = new MimeMultipart();
		BodyPart messgBodyPart = new MimeBodyPart();
		
		String htmlText = readContentFromFile(getClass().getClassLoader().getResourceAsStream("template/HTMLTemplate.html"));
		htmlText = replaceVariableValuesInHtmlString(htmlText, customerEntity,ticketDescription);
		
		messgBodyPart.setContent(htmlText, "text/html");
		multipart.addBodyPart(messgBodyPart);

        
        mailService.sendEmail(mail, multipart);
	}
	
	// Method to read HTML file as a String
	private static String readContentFromFile(InputStream in) {
		StringBuffer contents = new StringBuffer();

		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		try {

			String str = br.readLine();
			try {

				while (str != null) {

					contents.append(str);
					str = br.readLine();
				}
			} finally {
				in.close();
			}
		} catch (IOException ex) {
			System.out.println(ex.getStackTrace());

		}
		return contents.toString();
	}
	
	// method to replace variables in html string with real values
	private String replaceVariableValuesInHtmlString(String htmlTxt,Customer customerEntity,TicketDescription ticketDescription) {
		
		
		
		htmlTxt = htmlTxt.replace("value1", ticketDescription.getCustomer().getCustomerName());
		
		htmlTxt = htmlTxt.replace("value2", String.valueOf(ticketDescription.getId()));
		
		htmlTxt = htmlTxt.replace("value3", customerEntity.getMobileNumber());
		
		htmlTxt = htmlTxt.replace("value4", customerEntity.getEmail());
		
		htmlTxt = htmlTxt.replace("value5", ticketDescription.getTicketTitle());
		
		htmlTxt = htmlTxt.replace("value6", ticketDescription.getTicketDescription());
				
		String str = ticketDescription.getCreatedAt().toString();
		
		htmlTxt = htmlTxt.replace("value7", str); 
		
		return htmlTxt;
	}
	
	@PostMapping("/feedback")
	public ResponseEntity<TicketDescription> updateTicketFeedback(@RequestBody TicketDescriptionDTO ticketDescriptionDTO)  {
		ResponseEntity<TicketDescription> responseEntity = null;

		System.out.println("TicketDescriptionDTO=  "+  ticketDescriptionDTO.toString());

		if (ticketDescriptionDTO == null || ticketDescriptionDTO.getId() == null) {
			System.out.println("TicketDescriptionDTO or Customer ID Can Not Null");
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} 

		Optional<TicketDescription> entity =  ticketDescriptionRepository.findById(ticketDescriptionDTO.getId()) ;

		
			TicketDescription t1 = entity.get();
		
			if(ticketDescriptionDTO.getFeedback() != null) {
				t1.setFeedback(ticketDescriptionDTO.getFeedback());
			}	
			
			t1.setModifiedAt(new Timestamp(System.currentTimeMillis()));

			t1 =  ticketDescriptionRepository.save(t1);
			
			responseEntity = new ResponseEntity<TicketDescription>(t1, HttpStatus.OK);
			return responseEntity;
	}
	
	@PostMapping("/action-to-be-taken")
	public ResponseEntity<TicketDescription> updateTicketAction(@RequestBody TicketDescriptionDTO ticketDescriptionDTO)  {
		ResponseEntity<TicketDescription> responseEntity = null;

		System.out.println("TicketDescriptionDTO=  "+  ticketDescriptionDTO.toString());

		if (ticketDescriptionDTO == null || ticketDescriptionDTO.getId() == null) {
			System.out.println("TicketDescriptionDTO or Customer ID Can Not Null");
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} 

		Optional<TicketDescription> entity =  ticketDescriptionRepository.findById(ticketDescriptionDTO.getId()) ;

		
			TicketDescription t1 = entity.get();
			
			if(ticketDescriptionDTO.getAction() != null) {
				t1.setAction(ticketDescriptionDTO.getAction());
				t1.setTicketStatus("COMPLETED");
			}	

			
			t1.setModifiedAt(new Timestamp(System.currentTimeMillis()));

			t1 =  ticketDescriptionRepository.save(t1);
			
			responseEntity = new ResponseEntity<TicketDescription>(t1, HttpStatus.OK);
			return responseEntity;
	}
	
}