package com.adidas.publicservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.adidas.publicservice.controller.api.IPublicController;
import com.adidas.publicservice.model.SubscriptionDto;
import com.adidas.publicservice.service.PublicService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class PublicController implements IPublicController {
	
	@Autowired
	PublicService publicService;

	@Override
	public ResponseEntity<SubscriptionDto> saveSubscription(SubscriptionDto subscription) {
		var response = publicService.save(subscription);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@Override
	public ResponseEntity<Long> cancelSubscription(Long idSubscription) {
		var response = publicService.cancelSubscription(idSubscription);
		return ResponseEntity.status(HttpStatus.OK).body(response.getId());
	}

	@Override
	public ResponseEntity<SubscriptionDto> getSubscription(Long idSubscription) {
		var subscription = publicService.getSubscriptionById(idSubscription);
		return ResponseEntity.status(HttpStatus.OK).body(subscription);
	}

	@Override
	public ResponseEntity<List<SubscriptionDto>> listAll() {
		var response = publicService.listAll();
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

}
