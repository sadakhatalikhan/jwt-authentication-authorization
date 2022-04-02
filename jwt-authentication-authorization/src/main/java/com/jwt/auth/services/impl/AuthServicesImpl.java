package com.jwt.auth.services.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jwt.auth.dto.UserJwtDTO;
import com.jwt.auth.exception.SignUpException;
import com.jwt.auth.model.User;
import com.jwt.auth.repository.UserRepository;
import com.jwt.auth.request.LoginRequest;
import com.jwt.auth.request.SignUpRequest;
import com.jwt.auth.response.Response;
import com.jwt.auth.security.jwt.JwtTokenProvider;
import com.jwt.auth.services.AuthServices;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServicesImpl implements AuthServices {

	private final UserRepository userRepository;
	private final JwtTokenProvider jwtTokenProvider;
	private final AuthenticationManager authenticationManager;
	private final PasswordEncoder encoder;

	@Override
	public ResponseEntity<Response> createUser(SignUpRequest signupRequest) {

		if (userRepository.findByMobileNumber(signupRequest.getMobileNumber()).isPresent()) {
			throw new SignUpException(999, "Mobile number already registered.");
		}

		// create the user
		User userObject = userRepository.save(userModelMapper(signupRequest));

		return ResponseEntity.ok(Response.builder().code(0).message("User profile success created")
				.body(jwtResponse(userObject)).build());
	}

	@Override
	public ResponseEntity<Response> authenticateUser(LoginRequest loginRequest) {
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				loginRequest.getMobileNumber(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        CustomUserDetailsImpl userDetails = (CustomUserDetailsImpl) authentication.getPrincipal();
        User loginUser = userRepository.getOne(userDetails.getId());

        if (!loginUser.getVerified()) {
            return ResponseEntity
                    .badRequest()
                    .body(Response.builder()
                    		.message("User MobileNumber is not verified")
                    		.code(999)
                    		.build());
        }

        if (!loginUser.getActivated()) {
        	
            return ResponseEntity
                    .badRequest()
                    .body(Response.builder()
                    		.message("User is not activated")
                    		.code(999)
                    		.build());
        }
        return ResponseEntity.ok(Response.builder().code(0).message("User successfully logged in")
				.body(jwtResponse(loginUser)).build());
        
	}

	private User userModelMapper(SignUpRequest signupRequest) {
		return User.builder()
				.username(signupRequest.getUsername())
				.emailAddress(signupRequest.getEmailAddress())
				.mobileNumber(signupRequest.getMobileNumber())
				.password(encoder.encode(signupRequest.getPassword()))
				.activated(Boolean.TRUE)
				.verified(Boolean.TRUE)
				.role(signupRequest.getRole())
				.build();
	}

	private UserJwtDTO jwtResponse(User user) {
		return UserJwtDTO.builder()
				.type("Bearer")
				.token(jwtTokenProvider.generateJwtToken(user))
				.id(user.getId())
				.username(user.getUsername())
				.emailAddress(user.getEmailAddress())
				.mobileNumber(user.getMobileNumber())
				.activated(user.getActivated())
				.verified(user.getVerified())
				.createDate(user.getCreateDate())
				.modifyDate(user.getModifyDate())
				.role(user.getRole())
				.build();
	}
}
