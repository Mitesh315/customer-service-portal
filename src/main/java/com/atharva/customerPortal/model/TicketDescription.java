package com.atharva.customerPortal.model;

import com.atharva.customerPortal.model.Customer;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ticket_details")
public class TicketDescription {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "ticket_desc")
	private String ticketDescription;

	@Column(name = "ticket_title")
	private String ticketTitle;

	@Column(name = "ticket_status")
	private String ticketStatus = "INPROGRESS";

	@Column(name = "device")
	private String device;
	
	@Column(name = "action")
	private String action;

	@Column(name = "feedback")
	private String feedback;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "customer_id")
	private Customer customer;

	@Column(name = "created_at")
	private Timestamp createdAt;

	@Column(name = "modified_at")
	private Timestamp modifiedAt;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTicketDescription() {
		return ticketDescription;
	}

	public void setTicketDescription(String ticketDescription) {
		this.ticketDescription = ticketDescription;
	}

	public String getTicketTitle() {
		return ticketTitle;
	}

	public void setTicketTitle(String ticketTitle) {
		this.ticketTitle = ticketTitle;
	}

	public String getTicketStatus() {
		return ticketStatus;
	}

	public void setTicketStatus(String ticketStatus) {
		this.ticketStatus = ticketStatus;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getDevice() {
		return device;
	}

	public void setDevice(String device) {
		this.device = device;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public Timestamp getModifiedAt() {
		return modifiedAt;
	}

	public void setModifiedAt(Timestamp modifiedAt) {
		this.modifiedAt = modifiedAt;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	@Override
	public String toString() {
		return "TicketDescription [id=" + id + ", ticketDescription=" + ticketDescription + ", ticketTitle="
				+ ticketTitle + ", ticketStatus=" + ticketStatus + ", device=" + device + ", action=" + action
				+ ", feedback=" + feedback + ", customer=" + customer + ", createdAt=" + createdAt + ", modifiedAt="
				+ modifiedAt + "]";
	}
	
}
