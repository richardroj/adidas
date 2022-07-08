package com.adidas.subscriptionservice.controller.api;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.adidas.subscriptionservice.exception.ErrorResponse;
import com.adidas.subscriptionservice.model.Subscription;
import com.adidas.subscriptionservice.model.dto.SubscriptionDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

public interface ISubscriptionController {
	
	@Operation(summary = "/saveSubscription", description = "Operation to save subscription.", tags = "Subscription")
    @ApiResponse(responseCode = "201", description = "Returns Created",
            content = @Content(schema = @Schema(implementation = SubscriptionDto.class)))
    @ApiResponse(responseCode = "400", description = "Subscription validations don't succeed ",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    @PostMapping(value = "/subscriptions",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<SubscriptionDto> saveSubscription(@RequestBody SubscriptionDto subscription);
	
	@Operation(summary = "/cancelSubscription", description = "Operation to cancel subscription.", tags = "Subscription")
    @ApiResponse(responseCode = "200", description = "Returns Ok",
            content = @Content(schema = @Schema(implementation = SubscriptionDto.class)))
    @ApiResponse(responseCode = "400", description = "KO canceling ",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    @DeleteMapping(value = "/subscriptions/{idSubscription}",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Long> cancelSubscription(@PathVariable("idSubscription") Long idSubscription);
	
	@Operation(summary = "/getSubscription", description = "Operation to retrieve subscription.", tags = "Subscription")
    @ApiResponse(responseCode = "200", description = "Returns Ok",
            content = @Content(schema = @Schema(implementation = SubscriptionDto.class)))
    @ApiResponse(responseCode = "400", description = "Not exists ",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    @GetMapping(value = "/subscriptions/{idSubscription}",
            produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Subscription> getSubscription(@PathVariable("idSubscription") Long idSubscription);
	
	@Operation(summary = "/list", description = "Operation to retrieve subscriptions list.", tags = "Subscription")
    @ApiResponse(responseCode = "200", description = "Returns Ok",
            content = @Content(schema = @Schema(implementation = SubscriptionDto.class)))
    @ApiResponse(responseCode = "400", description = "Not data ",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    @GetMapping(value = "/subscriptions",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<Subscription>> listAll(@PathVariable("idSubscription") Long idSubscription);

}
