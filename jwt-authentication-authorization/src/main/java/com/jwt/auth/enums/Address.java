package com.jwt.auth.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Address {
	
	PERMANENT_ADDRESS("PERMANENT_ADDRESS"), CURRENT_ADDRESS("CURRENT_ADDRESS");

	private String status;

	private Address(String status) {
		this.status = status;
	}

	@JsonValue
	public String getStatus() {
		return status;
	}
}
