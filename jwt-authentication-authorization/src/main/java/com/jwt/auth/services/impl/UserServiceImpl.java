package com.jwt.auth.services.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jwt.auth.request.UserAddressRequest;
import com.jwt.auth.response.Response;
import com.jwt.auth.security.jwt.JwtUtils;
import com.jwt.auth.services.UserServices;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserServices{

	private final JwtUtils jwtUtils;
	
	@Override
	public ResponseEntity<Response> addUserAddress(UserAddressRequest address) {
		Long id = jwtUtils.getUserIdFromJwtToken();
		System.out.println("111111111111111 "+id);
		
		return null;
	}

}
