package com.adidas.subscriptionservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import com.adidas.subscriptionservice.controller.api.ISubscriptionController;
import com.adidas.subscriptionservice.model.Subscription;
import com.adidas.subscriptionservice.model.dto.SubscriptionDto;
import com.adidas.subscriptionservice.repository.SubscriptionRepository;

@RestController
public class SubscriptionController implements ISubscriptionController{
	
	@Autowired
	SubscriptionRepository subscriptionRepository;
	

	@Override
	public ResponseEntity<SubscriptionDto> saveSubscription(SubscriptionDto subscription) {	
		//SendEmail

		return ResponseEntity.status(HttpStatus.CREATED).body(subscription);
	}

	@Override
	public ResponseEntity<Long> cancelSubscription(Long idSubscription) {
		return ResponseEntity.status(HttpStatus.OK).body(1L);
	}

	@Override
	public ResponseEntity<Subscription> getSubscription(Long idSubscription) {
		var sub = subscriptionRepository.findById(idSubscription);
		return ResponseEntity.status(HttpStatus.OK).body(sub.get());
	}

	@Override
	public ResponseEntity<List<Subscription>> listAll(Long idSubscription) {
		// TODO Auto-generated method stub
		return null;
	}
}
