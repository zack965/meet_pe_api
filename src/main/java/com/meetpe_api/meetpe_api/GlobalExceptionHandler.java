package com.meetpe_api.meetpe_api;

import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;
import java.security.SignatureException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
        @ExceptionHandler(HttpMessageNotReadableException.class)
        public ResponseEntity<Map<String,String>> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
            // Log the error message
         //   ex.printStackTrace();

            // Return a meaningful error response
            Map<String, String> error = new HashMap<>();
            error.put("message", "Invalid JSON payload");
            return ResponseEntity.badRequest().body(error);
        }
        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
            BindingResult result = ex.getBindingResult();
            Map<String, String> errors = new HashMap<>();
            for (FieldError error : result.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(errors);
        }
        @ExceptionHandler(Exception.class)
        public ProblemDetail handleSecurityException(Exception exception) {
            ProblemDetail errorDetail = null;

            // TODO send this stack trace to an observability tool
            //exception.printStackTrace();

            if (exception instanceof BadCredentialsException) {
                errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(401), exception.getMessage());
                errorDetail.setProperty("description", "The username or password is incorrect");

                return errorDetail;
            }

            if (exception instanceof AccountStatusException) {
                errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(403), exception.getMessage());
                errorDetail.setProperty("description", "The account is locked");
            }

            if (exception instanceof AccessDeniedException) {
                errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(403), exception.getMessage());
                errorDetail.setProperty("description", "You are not authorized to access this resource");
            }

            if (exception instanceof SignatureException) {
                errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(403), exception.getMessage());
                errorDetail.setProperty("description", "The JWT signature is invalid");
            }

            if (exception instanceof ExpiredJwtException) {
                errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(403), exception.getMessage());
                errorDetail.setProperty("description", "The JWT token has expired");
            }

            if (errorDetail == null) {
                errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(500), exception.getMessage());
                errorDetail.setProperty("description", "Unknown internal server error.");
            }

            return errorDetail;
        }
}
