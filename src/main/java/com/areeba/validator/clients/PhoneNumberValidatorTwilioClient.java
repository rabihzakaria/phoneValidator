package com.areeba.validator.clients;

import com.areeba.validator.models.PhoneNumberDetailsResponse;
import com.twilio.Twilio;
import com.twilio.exception.ApiException;
import com.twilio.rest.lookups.v1.PhoneNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;


@Component
public class PhoneNumberValidatorTwilioClient {
	//TODO  encrypt the token and the sid values
	@Value("${twilio.api.token}")
	private  String token;
	@Value("${twilio.api.sid}")
	private  String sid;

	private  Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * Validates a phone number using the Twilio API.
	 *
	 * @param number The phone number to validate.
	 * @return The response containing phone number details.
	 */
	public ResponseEntity<PhoneNumberDetailsResponse> validateNumber(String number) {
		logger.info("Trying to validate the phone number {}",number);
		try {
			Twilio.init(sid, token);
			PhoneNumber phoneNumber = PhoneNumber.fetcher(
							new com.twilio.type.PhoneNumber(number))
					.fetch();
			return ResponseEntity.ok(new PhoneNumberDetailsResponse(phoneNumber));
		}catch (ApiException apiException) {
				logger.error("Error occurred while validating phone number: {}", apiException.getMessage(), apiException);
				String errorMessage = apiException.getMessage();
				HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
				if (errorMessage.contains("Invalid number") || errorMessage.contains("was not found")) {
					httpStatus = HttpStatus.BAD_REQUEST;
				}
				return ResponseEntity.status(httpStatus).build();
			} catch (Exception e) {
				logger.error("Unexpected error occurred while validating phone number: {}", e.getMessage(), e);
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			}
	}
}
