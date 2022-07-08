package com.adidas.subscriptionservice.service.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@FeignClient(name="email-service", url="http://email-service:9380/suite/email-service/v0/")
public interface EmailProxy {
	 @RequestMapping(method = RequestMethod.GET, value = "/emails/to/{to}/message/{message}/title/{title}", produces = "application/json")
	 void sendEmail(@PathVariable("to") String to, @PathVariable("message") String message, @PathVariable("title") String title);
	
}
