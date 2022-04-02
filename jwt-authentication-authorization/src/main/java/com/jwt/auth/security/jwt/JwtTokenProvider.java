package com.jwt.auth.security.jwt;

import java.util.Base64;
import java.util.Date;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.jwt.auth.exception.CustomException;
import com.jwt.auth.model.User;
import com.jwt.auth.services.impl.CustomUserDetailsImpl;
import com.jwt.auth.services.impl.CustomUserDetailsService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenProvider {

	@Value("${security.jwt.token.secret-key}")
	private String secretKey;

	@Value("${security.jwt.token.expire-length}")
	private long validityInMilliseconds;

	@Autowired
	private CustomUserDetailsService userDetailsService;
	
	@PostConstruct
	protected void init() {
		secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
	}

	public String generateJwtToken(User user) {
		Claims claims = Jwts.claims().setSubject(user.getId().toString());
		claims.put("mobileNumber", user.getMobileNumber());
		return Jwts.builder().setClaims(claims).setIssuedAt(new Date())
				.setExpiration(new Date((new Date()).getTime() + validityInMilliseconds))
				.signWith(SignatureAlgorithm.HS512, secretKey).compact();
	}
	
	public String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public boolean validateToken(String token) {
        Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
        return true;
    }
    
    public Authentication getAuthentication(String token) {
        Claims allClaims = this.getAllClaims(token);
        CustomUserDetailsImpl userDetails = (CustomUserDetailsImpl) 
        		userDetailsService.loadUserById(Optional.ofNullable(allClaims.getSubject())
        				.map(Long::valueOf)
        				.orElseThrow(() -> new CustomException("", HttpStatus.UNAUTHORIZED))
        		);
       
        if(userDetails.getActivated().equals(Boolean.FALSE)){
            throw new CustomException("", HttpStatus.UNAUTHORIZED);
        }
        
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }
    
    public Claims getAllClaims(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    }
}
