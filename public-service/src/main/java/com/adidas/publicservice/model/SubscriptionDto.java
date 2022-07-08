package com.adidas.publicservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class SubscriptionDto {

	private Long id;
	private String email;
	private String name;
	private String gender;
	private String dateOfBirth;
	private boolean consent;
	private boolean newsletter;
}
