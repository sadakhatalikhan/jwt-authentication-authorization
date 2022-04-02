package com.jwt.auth.services;

import org.springframework.http.ResponseEntity;

import com.jwt.auth.request.LoginRequest;
import com.jwt.auth.request.SignUpRequest;
import com.jwt.auth.response.Response;

public interface AuthServices {
	
	ResponseEntity<Response> createUser(SignUpRequest signupRequest);

	ResponseEntity<Response> authenticateUser(LoginRequest loginRequest);
}
