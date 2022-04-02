package com.jwt.auth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jwt.auth.request.UserAddressRequest;
import com.jwt.auth.response.Response;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "http://localhost:8100")
@RequiredArgsConstructor
public class UserController {
	
	@PostMapping("/add/address")
	public ResponseEntity<Response> addUserAddress(@RequestBody UserAddressRequest address) {
		return null;
	}
	
}
