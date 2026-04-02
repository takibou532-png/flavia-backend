package com.menu.demo.Exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler {
	 public ResponseEntity<Map<String, String>> handleResourceNotFound(ResourceNotFoundException ex) {
	        Map<String, String> error = new HashMap<>();
	        error.put("error", "Resource not found");
	        error.put("message", ex.getMessage());
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	    }

	    @ExceptionHandler(Exception.class)
	    public ResponseEntity<Map<String, String>> handleGeneric(Exception ex) {
	        Map<String, String> error = new HashMap<>();
	        error.put("error", "Internal Server Error");
	        error.put("message", ex.getMessage());
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
	    }
}
