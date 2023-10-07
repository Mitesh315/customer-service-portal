package com.atharva.customerPortal;

public class TicketDescriptionDTO {
	
	private Long id;
	
    private String ticketDescription;

    private String ticketTitle;

    private String email;
    
    private String device;
    
    private String feedback;
    
    private String action;
    
    

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDevice() {
		return device;
	}

	public void setDevice(String device) {
		this.device = device;
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
		return "TicketDescriptionDTO [id=" + id + ", ticketDescription=" + ticketDescription + ", ticketTitle="
				+ ticketTitle + ", email=" + email + ", device=" + device + ", feedback=" + feedback + ", action="
				+ action + "]";
	}

	

	
	
	
}
