package com.adidas.emailservice.service;

import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import com.adidas.emailservice.model.Email;

@Service
public class EmailService {

	private final Logger logger = Logger.getLogger(getClass().getName()); 
	
	public void sendMail(Email email) {
		logger.info("Sending email");
		
		logger.info("Email sent to user");
	}
}
