package com.jwt.auth.security.jwt;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jwt.auth.response.Response;

@Component
public class AuthEntryPointJwt implements AuthenticationEntryPoint {

    private static final Logger logger = LoggerFactory.getLogger(AuthEntryPointJwt.class);

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        Map<String ,String > rsp =new HashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();

        response.setStatus(HttpStatus.UNAUTHORIZED.value() );

        rsp.put("code", HttpStatus.UNAUTHORIZED.value() + "") ;
        rsp.put("msg", authException.getMessage()) ;
        rsp.put("path", request.getServletPath());
        rsp.put("timestamp", String.valueOf(System.currentTimeMillis()));

        response.setContentType("application/json;charset=UTF-8");

        response.getWriter().write(objectMapper.writeValueAsString(Response.builder().message(authException.getMessage()).code( HttpStatus.UNAUTHORIZED.value()).body(rsp).build()));
        response.getWriter().flush();
        response.getWriter().close();
    }

}