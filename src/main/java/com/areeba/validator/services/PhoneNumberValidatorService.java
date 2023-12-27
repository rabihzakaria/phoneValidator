package com.areeba.validator.services;

import com.areeba.validator.models.PhoneNumberDetailsResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

@Service
public class PhoneNumberValidatorService {
	@Value("api-key")
	private String apiKey;
	@Value("api-base-url")
	private String baseUrl;


	private PhoneNumberDetailsResponse validatePhoneNumber(String phoneNumber) throws IOException, MalformedURLException {
		String apiUrl = baseUrl+"/validate";

		URL url = new URL(apiUrl);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();

		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			StringBuilder response = new StringBuilder();
			String line;

			while ((line = reader.readLine()) != null) {
				response.append(line);
			}

			// Parse the JSON response
			return parseNumVerifyResponse(response.toString());
		} finally {
			connection.disconnect();
		}
	}

	private PhoneNumberDetailsResponse parseNumVerifyResponse(String jsonResponse) {
		// You can use a JSON parsing library (e.g., Gson or Jackson) for a more robust solution
		// For simplicity, this example uses basic string manipulation

		String[] parts = jsonResponse.split(",");
		boolean isValid = parts[1].split(":")[1].trim().equals("true");

		if (isValid) {
			String countryCode = parts[4].split(":")[1].trim();
			String countryName = parts[5].split(":")[1].trim();
			String operatorName = parts[9].split(":")[1].trim();

			return new PhoneNumberDetailsResponse();
		} else {
			throw new RuntimeException("Invalid phone number");
		}
	}
}
