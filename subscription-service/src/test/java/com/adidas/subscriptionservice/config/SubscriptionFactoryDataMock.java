package com.adidas.subscriptionservice.config;

import java.util.Date;
import java.util.List;

import com.adidas.subscriptionservice.model.Subscription;

public interface SubscriptionFactoryDataMock {
	static List<Subscription> createSubscriptionList() {
		var sub1 = new Subscription();
		sub1.setName("Test 1");
		sub1.setEmail("");
		sub1.setGender("H");
		sub1.setNewsletter(true);
		sub1.setDateOfBirth("1980-10-10");
		sub1.setConsent(true);
		
		
		var sub2 = new Subscription();
		sub2.setName("Test 2");
		sub2.setEmail("");
		sub2.setGender("H");
		sub2.setNewsletter(true);
		sub2.setDateOfBirth("1979-11-11");
		sub2.setConsent(true);
		
		return List.of(sub1,sub2);
	}

}
