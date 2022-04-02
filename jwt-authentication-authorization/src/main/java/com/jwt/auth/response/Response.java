package com.jwt.auth.response;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

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
@ToString
@Builder(toBuilder = true)
@JsonInclude(Include.NON_NULL)
public class Response implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer code;
	private String message;
	private Object body;
}
