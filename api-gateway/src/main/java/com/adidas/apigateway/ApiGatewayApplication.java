package com.adidas.apigateway;

import java.util.ArrayList;
import java.util.List;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.context.annotation.Bean;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@EnableDiscoveryClient
@OpenAPIDefinition(info =
@Info(title = "API Gateway", version = "1.0", description = "Documentation API Gateway v1.0")
)
public class ApiGatewayApplication {

	@Autowired
	RouteDefinitionLocator locator;

	@Bean
	public List<GroupedOpenApi> apis() {
		List<GroupedOpenApi> groups = new ArrayList<>();
		List<RouteDefinition> definitions = locator.getRouteDefinitions().collectList().block();
		assert definitions != null;
		definitions.stream().filter(routeDefinition -> routeDefinition.getId().matches(".*-route")).forEach(routeDefinition -> {
			String name = routeDefinition.getId().replaceAll("-route", "");
			groups.add(GroupedOpenApi.builder().pathsToMatch("/" + name + "/**").group(name).build());
		});
		return groups;
	}
	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

}
