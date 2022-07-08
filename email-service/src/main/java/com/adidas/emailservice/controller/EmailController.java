package com.adidas.emailservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.adidas.emailservice.model.Email;
import com.adidas.emailservice.service.EmailService;

@RestController
public class EmailController {
	
	@Autowired
	EmailService emailService;

	@GetMapping("/emails/to/{to}/message/{message}/title/{title}")
	public ResponseEntity<String> sendEmail(@PathVariable("to") String to, @PathVariable("message") String message, @PathVariable("title") String title){
		
		return ResponseEntity.status(HttpStatus.CREATED).body("Email sent");
	}
}
