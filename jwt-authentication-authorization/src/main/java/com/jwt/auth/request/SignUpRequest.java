package com.jwt.auth.request;

import java.io.Serializable;

import com.jwt.auth.enums.UserRole;

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
@ToString(exclude = "password")
@Builder(toBuilder = true)
public class SignUpRequest implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 9093130238565598813L;
	private String username;
	private String mobileNumber;
	private String emailAddress;
	private String password;
	private UserRole role;
}
