package com.jwt.auth.request;

import com.jwt.auth.enums.Address;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@ToString
public class UserAddressRequest {
	
	private String address;
	private String country;
	private String city;
	private Address addressType;
}