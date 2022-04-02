package com.jwt.auth.services;

import org.springframework.http.ResponseEntity;

import com.jwt.auth.request.UserAddressRequest;
import com.jwt.auth.response.Response;

public interface UserServices {
	
	ResponseEntity<Response> addUserAddress(UserAddressRequest address);
	
}
