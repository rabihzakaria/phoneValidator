package com.areeba.validator.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.twilio.rest.lookups.v1.PhoneNumber;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.net.URI;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PhoneNumberDetailsResponse implements Serializable {
	private Map<String, Object> callerName;

	private Map<String, Object> carrier;

	private String countryCode;

	private String nationalFormat;

	private String phoneNumber;

	private Map<String, Object> addOns;

	private URI url;

	public PhoneNumberDetailsResponse(PhoneNumber number){
		setPhoneNumber(number.getPhoneNumber().getEndpoint());
		setCallerName(number.getCallerName());
		setCountryCode(number.getCountryCode());
		setNationalFormat(number.getNationalFormat());
		setAddOns(number.getAddOns());
		setNationalFormat(getNationalFormat());
		setUrl(number.getUrl());
		setCarrier(number.getCarrier());
	}
}
