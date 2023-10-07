package com.atharva.customerPortal.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "customers")
public class Customer {
	
	  	@Id
	  	@GeneratedValue(strategy = GenerationType.IDENTITY)
	    private long id;

	    @Column(name = "customer_name")
	    private String customerName;

	    @Column(name = "email")
	    private String email;

	    @Column(name = "password")
	    private String password;
	    
	    @Column(name = "mobile_number")
	    private String mobileNumber;

		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

		public String getCustomerName() {
			return customerName;
		}

		public void setCustomerName(String customerName) {
			this.customerName = customerName;
		}


		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getMobileNumber() {
			return mobileNumber;
		}

		public void setMobileNumber(String mobileNumber) {
			this.mobileNumber = mobileNumber;
		}

		@Override
		public String toString() {
			return "Customer [id=" + id + ", customerName=" + customerName + ", email=" + email + ", password="
					+ password + ", mobileNumber=" + mobileNumber + "]";
		}	    
}
