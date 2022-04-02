package com.jwt.auth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jwt.auth.request.LoginRequest;
import com.jwt.auth.request.SignUpRequest;
import com.jwt.auth.response.Response;
import com.jwt.auth.services.AuthServices;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:8100")
@RequiredArgsConstructor
public class AuthController {

	private final AuthServices authServices;
	
	@PostMapping("/create/user")
	public ResponseEntity<Response> createUser(@RequestBody SignUpRequest signupRequest) {
		return authServices.createUser(signupRequest);
	}
	
	@PostMapping("/user/login")
	public ResponseEntity<Response> authenticateUser(@RequestBody LoginRequest loginRequest) {
		return authServices.authenticateUser(loginRequest);
	}
	
}
