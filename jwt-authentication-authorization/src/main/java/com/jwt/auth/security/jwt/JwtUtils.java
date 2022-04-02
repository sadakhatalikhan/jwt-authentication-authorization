package com.jwt.auth.security.jwt;

import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.jwt.auth.enums.UserRole;
import com.jwt.auth.repository.UserRepository;
import com.jwt.auth.services.impl.CustomUserDetailsImpl;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class JwtUtils {
  
    public String getMobileFromJwtToken() {

        return Optional.ofNullable(SecurityContextHolder.getContext())
                .map(SecurityContext::getAuthentication)
                .map(Authentication::getPrincipal)
                .filter(principal-> principal instanceof CustomUserDetailsImpl)
                .map(userDetails-> ((CustomUserDetailsImpl) userDetails).getMobileNumber())
                .orElseGet(()-> "");
    }

    public Long getUserIdFromJwtToken() {

        return Optional.ofNullable(SecurityContextHolder.getContext())
                .map(SecurityContext::getAuthentication)
                .map(Authentication::getPrincipal)
                .filter(principal-> principal instanceof CustomUserDetailsImpl)
                .map(userDetail-> ((CustomUserDetailsImpl) userDetail).getId())
                .orElse(null);
    }


   public boolean isUserLoggedIn(){

        return Optional.ofNullable(SecurityContextHolder.getContext())
                .map(SecurityContext::getAuthentication)
                .map(Authentication::getPrincipal)
                .filter(principal-> principal instanceof CustomUserDetailsImpl)
                .map(userDetails-> ((CustomUserDetailsImpl) userDetails))
                .map(CustomUserDetailsImpl::getRoles)
                .filter(roles -> roles.contains(UserRole.TLUSER) || roles.contains(UserRole.TLADMIN))
                .map(value-> true)
                .orElse(false);
    }

    public String getUserRole(){

        return Optional.ofNullable(SecurityContextHolder.getContext())
                .map(SecurityContext::getAuthentication)
                .map(Authentication::getAuthorities)
                .map(stream-> stream.stream().map(GrantedAuthority::getAuthority).findFirst().orElse(""))
                .orElse("");
    }

}
