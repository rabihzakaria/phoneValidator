package com.areeba.validator.controllers;

import com.areeba.validator.clients.PhoneNumberValidatorTwilioClient;
import com.areeba.validator.models.PhoneNumberDetailsResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/validate")
public class PhoneNumberValidatorController {

	private final        PhoneNumberValidatorTwilioClient client;
	private final        Logger                           logger             = LoggerFactory.getLogger(getClass());
	public PhoneNumberValidatorController(PhoneNumberValidatorTwilioClient client) {
		this.client = client;
	}

	@GetMapping(value = "/{phoneNumber}")
	public ResponseEntity<PhoneNumberDetailsResponse> validate(@PathVariable("phoneNumber") String phoneNumber) {
		return client.validateNumber(phoneNumber);
	}
}
