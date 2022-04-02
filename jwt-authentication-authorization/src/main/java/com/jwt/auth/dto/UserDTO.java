package com.jwt.auth.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

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
public class UserDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2906131052888247847L;
	
	private Long id;
	private String username;
	private String emailAddress;
	private String mobileNumber;
	private Boolean activated;
	private Boolean verified;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createDate;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date modifyDate;
}
