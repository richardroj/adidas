package com.adidas.publicservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;
import com.adidas.publicservice.model.SubscriptionDto;
import com.google.protobuf.Empty;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.service.SubscriptionIdRequest;
import io.grpc.service.SubscriptionRequest;
import io.grpc.service.SubscriptionServiceGrpc;

@Service
public class PublicService {
	
	private final Logger logger = Logger.getLogger(getClass().getName()); 
	
	public SubscriptionDto getSubscriptionById(Long id) {
		
		ManagedChannel channel = ManagedChannelBuilder.forAddress("subscription-service", 9090)
                .usePlaintext()
                .build();
		
		SubscriptionServiceGrpc.SubscriptionServiceBlockingStub service = SubscriptionServiceGrpc.newBlockingStub(channel);
		
		SubscriptionIdRequest request = SubscriptionIdRequest.newBuilder().setId(String.valueOf(id)).build();
		
		logger.info("Request " + request);
		
		var response = service.getSubscription(request);
		
		return new SubscriptionDto()
				.setConsent(Boolean.valueOf(response.getConsent()))
				.setDateOfBirth(response.getBirthDate())
				.setEmail(response.getEmail())
				.setId(Long.valueOf(response.getId()))
				.setName(response.getName())
				.setNewsletter(Boolean.valueOf(response.getNewsletter()))
				.setConsent(Boolean.valueOf(response.getConsent()))
				.setGender(response.getGender());
		
	}
	
	public SubscriptionDto save(SubscriptionDto dto){
		ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 9090)
                .usePlaintext()
                .build();
		
		SubscriptionServiceGrpc.SubscriptionServiceBlockingStub service = SubscriptionServiceGrpc.newBlockingStub(channel);
		
		SubscriptionRequest request = SubscriptionRequest.newBuilder()
				.setBirthDate(dto.getDateOfBirth().toString())
				.setName(dto.getName())
				.setConsent(String.valueOf(dto.isConsent()))
				.setNewsletter(String.valueOf(dto.isNewsletter()))
				.setEmail(dto.getEmail())
				.setGender(dto.getGender())
				.build();
		
		logger.info("Request " + request);
		
		var response = service.saveSubscription(request);
		
		return new SubscriptionDto()
				.setConsent(Boolean.valueOf(response.getConsent()))
				.setDateOfBirth(response.getBirthDate())
				.setEmail(response.getEmail())
				.setId(Long.valueOf(response.getId()))
				.setName(response.getName())
				.setNewsletter(Boolean.valueOf(response.getNewsletter()))
				.setConsent(Boolean.valueOf(response.getConsent()))
				.setGender(response.getGender());
		
	}
	
	public SubscriptionDto cancelSubscription(Long id) {
		
		ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 9090)
                .usePlaintext()
                .build();
		
		SubscriptionServiceGrpc.SubscriptionServiceBlockingStub service = SubscriptionServiceGrpc.newBlockingStub(channel);
		
		SubscriptionIdRequest request = SubscriptionIdRequest.newBuilder().setId(String.valueOf(id)).build();
		
		logger.info("Request " + request);
		
		var response = service.getSubscription(request);
		
		service.cancelSubscription(request);
		
		return new SubscriptionDto()
				.setConsent(Boolean.valueOf(response.getConsent()))
				.setDateOfBirth(response.getBirthDate())
				.setEmail(response.getEmail())
				.setId(Long.valueOf(response.getId()))
				.setName(response.getName())
				.setNewsletter(Boolean.valueOf(response.getNewsletter()))
				.setConsent(Boolean.valueOf(response.getConsent()))
				.setGender(response.getGender());
		
	}
	
	public List<SubscriptionDto> listAll() {
		
		ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 9090)
                .usePlaintext()
                .build();
		
		SubscriptionServiceGrpc.SubscriptionServiceBlockingStub service = SubscriptionServiceGrpc.newBlockingStub(channel);
		
		var response = service.listSubscriptions(Empty.newBuilder().build());
		
		var listSubscriptions = new ArrayList<SubscriptionDto>();
		response.getItemsList().forEach(sub -> {
			var subscription = new SubscriptionDto()
			.setConsent(Boolean.valueOf(sub.getConsent()))
			.setDateOfBirth(sub.getBirthDate())
			.setEmail(sub.getEmail())
			.setId(Long.valueOf(sub.getId()))
			.setName(sub.getName())
			.setNewsletter(Boolean.valueOf(sub.getNewsletter()))
			.setConsent(Boolean.valueOf(sub.getConsent()))
			.setGender(sub.getGender());
			listSubscriptions.add(subscription);
		});
		
		return listSubscriptions;
		
	}

}
