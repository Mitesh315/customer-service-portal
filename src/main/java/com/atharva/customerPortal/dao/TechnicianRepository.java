package com.atharva.customerPortal.dao;

import com.atharva.customerPortal.model.Technician;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

	@Repository
	public interface TechnicianRepository extends JpaRepository<Technician, Long> {

		Technician findByTechnicianLoginId(String technicianLoginId);

}
