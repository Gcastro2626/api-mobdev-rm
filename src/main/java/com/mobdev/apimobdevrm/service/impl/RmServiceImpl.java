package com.mobdev.apimobdevrm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.Objects;
import com.mobdev.apimobdevrm.converter.RmConverter;
import com.mobdev.apimobdevrm.facade.RmFacade;
import com.mobdev.apimobdevrm.model.Error;
import com.mobdev.apimobdevrm.model.RequestRm;
import com.mobdev.apimobdevrm.model.ResponseRm;
import com.mobdev.apimobdevrm.service.RmService;
import org.springframework.http.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Service
public class RmServiceImpl implements RmService {

	private static Logger logger = LogManager.getLogger(RmServiceImpl.class);
	
	@Autowired
	private RmFacade rmFacade;

	@Autowired
	private RmConverter rmConverter;

	@Override
	public ResponseEntity<?> getRickMorty(RequestRm requestRm) {
		ResponseEntity<?> response = null;
		ResponseRm result = new ResponseRm();
		try {
				String resultCharacter = rmFacade.getRickMortyCharacter(requestRm);
				String resultLocation = rmFacade.getRickMortyLocation(requestRm);
				result = rmConverter.responseRmConverter(resultCharacter, resultLocation);
				if (!Objects.isNull(result)) {
					response = ResponseEntity.status(HttpStatus.OK).
							   body(result);
				} else {
					Error error = new Error();
					error.setLocation(resultLocation);
					error.setCharacter(resultCharacter);
					response = ResponseEntity.status(HttpStatus.NOT_FOUND)
							   .body(error);
				}
		} catch (Exception e) {
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.toString());
			logger.error(e.toString());
		}
		return response;
	}

}
