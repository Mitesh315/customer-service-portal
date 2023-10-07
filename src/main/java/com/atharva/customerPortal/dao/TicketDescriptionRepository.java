package com.atharva.customerPortal.dao;

import java.util.List;

import com.atharva.customerPortal.model.TicketDescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketDescriptionRepository extends JpaRepository<TicketDescription, Long> {

	List<TicketDescription> findByCustomerId(Long id); 
	
}
