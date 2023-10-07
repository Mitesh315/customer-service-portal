package com.atharva.customerPortal.mailService;

import javax.mail.Multipart;

public interface MailService {
	public void sendEmail(Mail mail, Multipart multipart);
}
