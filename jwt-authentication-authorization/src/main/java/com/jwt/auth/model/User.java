package com.jwt.auth.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.jboss.aerogear.security.otp.api.Base32;

import com.jwt.auth.enums.UserRole;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@Builder(toBuilder = true)
@ToString(exclude = "password")
@Entity
@Table(name = "users", 
				uniqueConstraints = {@UniqueConstraint(columnNames = {"mobileNumber"})}
		)
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String username;
	private String emailAddress;
	private String mobileNumber;
	private String password;
	@Column(name = "activated", columnDefinition = "boolean default false")
	private Boolean activated;
	@Column(name = "verified", columnDefinition = "boolean default false")
	private Boolean verified;
	@Enumerated(EnumType.STRING)
	private UserRole role;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;
	
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifyDate;
	
	private boolean isUsing2FA;
    private String secret;
    
    public User() {
        super();
        this.secret = Base32.random();
    }
	
}
