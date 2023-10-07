package com.atharva.customerPortal.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "technicians")
public class Technician {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "technician_name")
	private String technicianName;
	
	@Column(name = "technician_login_id")
	private String technicianLoginId;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "technician_mobile_no")
	private String technicianMobileNo;
	
	

	
	public String getTechnicianName() {
		return technicianName;
	}

	public void setTechnicianName(String technicianName) {
		this.technicianName = technicianName;
	}

	public String getTechnicianLoginId() {
		return technicianLoginId;
	}

	public void setTechnicianLoginId(String technicianLoginId) {
		this.technicianLoginId = technicianLoginId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTechnicianMobileNo() {
		return technicianMobileNo;
	}

	public void setTechnicianMobileNo(String technicianMobileNo) {
		this.technicianMobileNo = technicianMobileNo;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Technician [id=" + id + ", technicianName=" + technicianName + ", technicianLoginId="
				+ technicianLoginId + ", password=" + password + ", technicianMobileNo=" + technicianMobileNo + "]";
	}


}







