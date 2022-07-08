package com.adidas.subscriptionservice.service;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;

import com.adidas.subscriptionservice.config.H2JpaConfig;
import com.adidas.subscriptionservice.config.SubscriptionFactoryDataMock;
import com.adidas.subscriptionservice.model.Subscription;
import com.adidas.subscriptionservice.repository.SubscriptionRepository;



@SpringBootTest(classes = { SubscriptionService.class, SubscriptionRepository.class })
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@Import({ H2JpaConfig.class })
public class SubscriptionServiceTest {
	
	@Autowired
	SubscriptionService subscriptionService;
	
	
	@Autowired
	SubscriptionRepository subscriptionRepository;

	
	private List<Subscription> insertMockData() {
		return (List<Subscription>) subscriptionRepository
				.saveAll(SubscriptionFactoryDataMock.createSubscriptionList());
	}
	
	@Test
	@DisplayName("SubscriptionService:findAll")
	public void findAll() {
		/*var subscriptions = this.insertMockData();
		var current = subscriptionService.list();
		
		Assertions.assertNotNull(subscriptions);
		Assertions.assertEquals(subscriptions.size(), 2);
		Assertions.assertEquals(current.get(1).getName(), subscriptions.get(1).getName());*/
	}
	
	@Test
	@DisplayName("SubscriptionService: Save")
	public void saveItShouldReturnOK(){
		
		/*var sub1 = createSubscription("TestSave");
		
		sub1 = subscriptionService.save(sub1);
		Assertions.assertNotNull(sub1.getId());*/
	}
	
	@Test
	@DisplayName("SubscriptionService: Get By Id")
	public void getByIdItShouldReturnObject(){
		
		/*var sub1 = createSubscription("Test-Get");
		
		sub1 = subscriptionService.findById(1l);
		
		Assertions.assertNotNull(sub1);*/
		
	}
	
	private Subscription createSubscription(String name) {
		
		var sub1 = new Subscription();
		sub1.setName(name);
		sub1.setEmail("");
		sub1.setGender("H");
		sub1.setNewsletter(true);
		sub1.setDateOfBirth("1976-08-08");
		sub1.setConsent(true);
		
		return sub1;
		
	}
	

}
