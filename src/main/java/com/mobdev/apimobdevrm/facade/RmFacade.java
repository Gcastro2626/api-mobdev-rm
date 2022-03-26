package com.mobdev.apimobdevrm.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.mobdev.apimobdevrm.config.PropertiesConfig;
import com.mobdev.apimobdevrm.model.RequestRm;

@Service
public class RmFacade {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private PropertiesConfig propertiesConfig;

	public ResponseEntity<?> getRickMortyCharacter(RequestRm requestrm) {
		ResponseEntity<?> response = null;
		try {
			HttpHeaders headers = generateHeaders();
			HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
			response = restTemplate.getForEntity(propertiesConfig.propertiesApiRm().getUrl().concat("/character/"+requestrm.getId()), ResponseEntity.class,
					requestEntity);

		} catch (Exception ex) {
			// logger.info(ex.getMessage());
		}
		return response;
	}
	
	public ResponseEntity<?> getRickMortyLocation(RequestRm requestrm) {
		ResponseEntity<?> response = null;
		try {
			HttpHeaders headers = generateHeaders();
			HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
			response = restTemplate.getForEntity(propertiesConfig.propertiesApiRm().getUrl().concat("/location/"+requestrm.getId()), ResponseEntity.class,
					requestEntity);

		} catch (Exception ex) {
			// logger.info(ex.getMessage());
		}
		return response;
	}

	private HttpHeaders generateHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		return headers;
	}

}
