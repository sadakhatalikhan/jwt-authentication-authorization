package com.jwt.auth.exception;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.social.NotAuthorizedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jwt.auth.response.Response;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Description - this exception has been implemented to handle the signUp Exceptions.
     * @param request
     * @param exception
     * @return
     */
    @ExceptionHandler(SignUpException.class)
    public ResponseEntity<Response> handleUserInviteException(SignUpException exception) {
    	return ResponseEntity.badRequest().body(
    				Response.builder()
    					.message(exception.getMessage())
    					.code(exception.getCode())
    					.build());
    }
    
    @ExceptionHandler(CustomException.class)
    public void handleCustomException(HttpServletResponse res, CustomException ex) throws IOException {
        res.sendError(ex.getHttpStatus().value(), ex.getMessage());

    }
    
    @ExceptionHandler(NotAuthorizedException.class)
    public void handleSocialAuthorizationException(HttpServletResponse res) throws IOException {
        res.sendError(HttpStatus.UNAUTHORIZED.value(), "Token is Invalid");
    }

    @ExceptionHandler(Exception.class)
    public void handleException(HttpServletResponse res) throws IOException {
        res.sendError(HttpStatus.BAD_REQUEST.value(), "Something went wrong");
    }
    
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Response> handleValidationExceptions(BadCredentialsException ex) {
        return ResponseEntity
                .badRequest()
                .body(Response.builder().message("Invalid Username or Password").code(999).build());
    }
    

}
