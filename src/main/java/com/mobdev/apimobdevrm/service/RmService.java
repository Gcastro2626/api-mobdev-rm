package com.mobdev.apimobdevrm.service;

import org.springframework.http.ResponseEntity;
import com.mobdev.apimobdevrm.model.RequestRm;

public interface RmService {

	ResponseEntity<?> getRickMorty(RequestRm requestRm);
}
