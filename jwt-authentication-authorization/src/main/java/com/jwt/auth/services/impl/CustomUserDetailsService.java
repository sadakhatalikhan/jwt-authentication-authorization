package com.jwt.auth.services.impl;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jwt.auth.model.User;
import com.jwt.auth.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

	private final UserRepository userRepository;
		
	@Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByMobileNumber(username)
        			.map(this::getUserDetails)
        			.orElseThrow(() -> new UsernameNotFoundException("User Not Found with mobile number: " + username));
    }

	 @Transactional
	    public UserDetails loadUserById(Long id) {
	        return userRepository.findById(id).map(this::getUserDetails)
	                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with mobile number: " + id));
	    }
	
	 private CustomUserDetailsImpl getUserDetails(User user) {
	        return CustomUserDetailsImpl.builder()
	                .id(user.getId())
	                .username(user.getUsername())
	                .password(user.getPassword())
	                .mobileNumber(user.getMobileNumber())
	                .roles(List.of(user.getRole()))
	                .verified(user.getVerified())
	                .emailAddress(user.getEmailAddress())
	                .activated(user.getActivated())
	                .build();
	    }
}
