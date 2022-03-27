package com.mobdev.apimobdevrm.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import com.mobdev.apimobdevrm.config.PropertiesConfig;
import com.mobdev.apimobdevrm.model.RequestRm;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Service
public class RmFacade {

	private static Logger logger = LogManager.getLogger(RmFacade.class);

	private RestTemplate restTemplate;

	@Autowired
	private PropertiesConfig propertiesConfig;

	public RmFacade(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.build();
	}
	//Call service rickyMorty Character
	public String getRickMortyCharacter(RequestRm requestrm) throws JSONException  {
		String response = "";
		try {
			HttpHeaders headers = generateHeaders();
			HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
			ResponseEntity<?>  resultApi = restTemplate.exchange(propertiesConfig.propertiesApiRm().getApiUrl().concat("character/"+requestrm.getId()),HttpMethod.GET,requestEntity,
			String.class);
			response  = resultApi.getBody().toString();
		} catch (HttpStatusCodeException e) {
			logger.error("Error consuming service api rickyMorty "+ e.getMessage());
			response  =  e.getMessage();
        } catch (Exception ex) {
			logger.error("Error consuming service api rickyMorty "+ ex.getMessage());
		}
		return response;
	}

	//Call service rickyMorty Location
	public String getRickMortyLocation(RequestRm requestrm) {
		String response = "";
		try {
			HttpHeaders headers = generateHeaders();
			HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
			ResponseEntity<?>  resultApi = restTemplate.exchange(propertiesConfig.propertiesApiRm().getApiUrl().concat("location/"+requestrm.getId()),HttpMethod.GET,requestEntity,
			String.class);
			response  = resultApi.getBody().toString();
		} catch (HttpStatusCodeException e) {
			response  =  e.getMessage();
			logger.error("Error consuming service api rickyMorty "+ e.getMessage());
		} catch (Exception ex) {
			logger.error("Error consuming service api rickyMorty "+ ex.getMessage());
		}
		return response;
	}
	
	private HttpHeaders generateHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		return headers;
	}
	
	
}
