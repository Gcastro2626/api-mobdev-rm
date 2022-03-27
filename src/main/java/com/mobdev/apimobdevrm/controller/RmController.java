package com.mobdev.apimobdevrm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.mobdev.apimobdevrm.service.RmService;
import com.mobdev.apimobdevrm.model.RequestRm;

@RestController
public class RmController {

	
	@Autowired
	private RmService rmService;

	@GetMapping(path = "/getRickMorty", produces = "application/json")
	public ResponseEntity<?> getRickMorty(@RequestBody RequestRm requestRm) throws Exception {
		ResponseEntity<?> response = rmService.getRickMorty(requestRm);
		return response;
	}
}
