package com.jwt.auth.enums;

import java.util.stream.Stream;

import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum UserRole implements GrantedAuthority {

	TLUSER("TLUSER"), TLADMIN("TLADMIN");

	private String status;

	private UserRole(String status) {
		this.status = status;
	}

	@JsonValue
	public String getStatus() {
		return status;
	}

	@Override
	public String getAuthority() {
		return name();
	}

	@JsonCreator
	public static UserRole forValue(String value) {
		return Stream.of(UserRole.values())
				.filter(enumValue -> enumValue.name().equals(value.toUpperCase()))
				.findFirst()
				.orElseThrow(IllegalArgumentException::new);
	}
}
