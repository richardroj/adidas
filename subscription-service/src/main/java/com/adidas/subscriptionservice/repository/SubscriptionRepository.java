package com.adidas.subscriptionservice.repository;

import org.springframework.data.repository.CrudRepository;

import com.adidas.subscriptionservice.model.Subscription;

public interface SubscriptionRepository extends CrudRepository<Subscription, Long>{
	
	Subscription findByName(String name);
	

}
