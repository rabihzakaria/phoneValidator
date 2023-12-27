package com.areeba.validator.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Carrier {
	private String errorCode;
	private String mobileCountryCode;
	private String mobileNetworkCode;
	private String name;
	private String type;
}
