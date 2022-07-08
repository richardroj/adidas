package com.adidas.subscriptionservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;

import com.adidas.subscriptionservice.model.Subscription;
import com.adidas.subscriptionservice.model.dto.EmailDto;
import com.adidas.subscriptionservice.repository.SubscriptionRepository;
import com.adidas.subscriptionservice.service.proxy.EmailProxy;
import com.google.protobuf.Empty;

import io.grpc.Status;
import io.grpc.StatusException;
import io.grpc.service.SubscriptionIdRequest;
import io.grpc.service.SubscriptionRequest;
import io.grpc.service.SubscriptionResponse;
import io.grpc.service.SubscriptionServiceGrpc;
import io.grpc.service.SubscriptionsResponseList;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class SubscriptionService extends SubscriptionServiceGrpc.SubscriptionServiceImplBase {
	
	private final Logger logger = Logger.getLogger(getClass().getName());
	
	@Autowired
	SubscriptionRepository subscriptionRepository;
	@Autowired 
	EmailProxy emailProxy;


	@Override
	public void getSubscription(SubscriptionIdRequest request, StreamObserver<SubscriptionResponse> responseObserver) {
		logger.info("Receiving id: " +request.getId());	
		var sub = subscriptionRepository.findById(Long.valueOf(request.getId()));

		if (sub.isPresent()) {
			var subscritpion = sub.get();

			SubscriptionResponse response = SubscriptionResponse.newBuilder()
					.setEmail(subscritpion.getEmail())
					.setGender(subscritpion.getGender())
					.setName(subscritpion.getName())
					.setId(String.valueOf(subscritpion.getId()))
					.setConsent(String.valueOf(subscritpion.isConsent()))
					.setNewsletter(String.valueOf(subscritpion.isNewsletter()))
					.setBirthDate("1910-11-11")
					.build();
		
			responseObserver.onNext(response);
			
			responseObserver.onCompleted();
		} else {
			responseObserver.onError(new StatusException(Status.NOT_FOUND));
		}
		
		logger.info("Finished calling Subscription API service..");
	}

	@Override
	public void saveSubscription(SubscriptionRequest request, StreamObserver<SubscriptionResponse> responseObserver) {
		logger.info("Receiving id: " +request);	
		//SendEmail
		var email = new EmailDto().setMessage("Created Subscription")
				.setTitle("Adidas Subscription")
				.setTo(request.getEmail());
		logger.info("Sending email to client"+ request.getName());
		emailProxy.sendEmail(email.getTo(), email.getMessage(), email.getTitle());
		var sub = new Subscription()
				.setConsent(false)
				.setDateOfBirth(request.getBirthDate())
				.setEmail(request.getEmail())
				.setGender(request.getGender())
				.setName(request.getName())
				.setNewsletter(Boolean.valueOf(request.getNewsletter()));
	
		sub = subscriptionRepository.save(sub);

		if (sub != null) {

			SubscriptionResponse response = SubscriptionResponse.newBuilder()
					.setEmail(sub.getEmail())
					.setGender(sub.getGender())
					.setName(sub.getName())
					.setId(String.valueOf(sub.getId()))
					.setConsent(String.valueOf(sub.isConsent()))
					.setNewsletter(String.valueOf(sub.isNewsletter()))
					.setBirthDate("1910-11-11")
					.build();
		
			responseObserver.onNext(response);
			
			responseObserver.onCompleted();
		} else {
			responseObserver.onError(new StatusException(Status.NOT_FOUND));
		}
		
		logger.info("Finished calling Subscription API service..");

	}

	@Override
	public void cancelSubscription(SubscriptionIdRequest request,
			StreamObserver<SubscriptionResponse> responseObserver) {
		logger.info("Receiving id: " +request.getId());	
		var sub = subscriptionRepository.findById(Long.valueOf(request.getId()));
		subscriptionRepository.deleteById(Long.valueOf(request.getId()));

		if (sub.isPresent()) {
			var subscription = sub.get();

			SubscriptionResponse response = SubscriptionResponse.newBuilder()
					.setEmail(subscription.getEmail())
					.setGender(subscription.getGender())
					.setName(subscription.getName())
					.setId(String.valueOf(subscription.getId()))
					.setConsent(String.valueOf(subscription.isConsent()))
					.setNewsletter(String.valueOf(subscription.isNewsletter()))
					.setBirthDate("1910-11-11")
					.build();
		
			responseObserver.onNext(response);
			
			responseObserver.onCompleted();
		} else {
			responseObserver.onError(new StatusException(Status.NOT_FOUND));
		}
		
		logger.info("Finished calling Subscription API service..");
	}

	@Override
	public void listSubscriptions(Empty request, StreamObserver<SubscriptionsResponseList> responseObserver) {
		logger.info("List All ");	
		var sub = subscriptionRepository.findAll();
		
		List<SubscriptionResponse> list = new ArrayList<>();
		sub.forEach(subscription -> {
			SubscriptionResponse response = SubscriptionResponse.newBuilder()
					.setEmail(subscription.getEmail())
					.setGender(subscription.getGender())
					.setName(subscription.getName())
					.setId(String.valueOf(subscription.getId()))
					.setConsent(String.valueOf(subscription.isConsent()))
					.setNewsletter(String.valueOf(subscription.isNewsletter()))
					.setBirthDate(subscription.getDateOfBirth())
					.build();
			
			list.add(response);
		});
		
		SubscriptionsResponseList listOfSubscriptions= SubscriptionsResponseList.newBuilder()
				.addAllItems(list).build();
				
		
		if (listOfSubscriptions.getItemsList().size()>0) {
			logger.info("List All: "+ listOfSubscriptions);
			responseObserver.onNext(listOfSubscriptions);
			
			responseObserver.onCompleted();
		} else {
			responseObserver.onError(new StatusException(Status.NOT_FOUND));
		}
		
		logger.info("Finished calling Subscription API service..");
	}

}
