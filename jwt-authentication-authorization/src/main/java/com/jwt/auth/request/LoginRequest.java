package com.jwt.auth.request;

import java.io.Serializable;

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
@ToString(exclude = "password")
public class LoginRequest implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7078541081481532816L;
	private String mobileNumber;
	private String password;
	
	
}
