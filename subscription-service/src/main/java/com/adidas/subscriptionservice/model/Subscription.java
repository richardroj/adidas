package com.adidas.subscriptionservice.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Entity
@Table(name="subscription")
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Subscription {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String email;
	private String name;
	private String gender;
	@Column(name="date_of_birth")
	private String dateOfBirth;
	private boolean consent;
	private boolean newsletter;
}
