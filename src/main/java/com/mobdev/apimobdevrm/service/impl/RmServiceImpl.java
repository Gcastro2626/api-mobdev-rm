package com.mobdev.apimobdevrm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.mobdev.apimobdevrm.facade.RmFacade;
import com.mobdev.apimobdevrm.model.RequestRm;
import com.mobdev.apimobdevrm.service.RmService;

@Service
public class RmServiceImpl implements RmService{

	@Autowired
	private RmFacade rmFacade;

	public ResponseEntity<?> getRickMorty(RequestRm requestRm) {
		ResponseEntity<?> result = rmFacade.getRickMortyCharacter(requestRm);
		return ResponseEntity.status(result.getStatusCode()).body(result.getBody());

	}

}
