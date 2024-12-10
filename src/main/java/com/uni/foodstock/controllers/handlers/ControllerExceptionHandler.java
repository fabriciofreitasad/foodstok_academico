package com.uni.foodstock.controllers.handlers;

import java.time.Instant;

import com.mysql.cj.jdbc.exceptions.MysqlDataTruncation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.uni.foodstock.dto.CustomError;
import com.uni.foodstock.services.exceptions.DatabaseException;
import com.uni.foodstock.services.exceptions.ResourceNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ControllerExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<CustomError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		CustomError err = new CustomError(Instant.now(), status.value(), e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	@ExceptionHandler(MysqlDataTruncation.class)
	public ResponseEntity<String> handleDataTruncationError(MysqlDataTruncation ex) {
		String errorMessage = "Imagem não suportada.";
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
	}
	
	@ExceptionHandler(DatabaseException.class)
	public ResponseEntity<CustomError> dataBase(DatabaseException e, HttpServletRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		CustomError err = new CustomError(Instant.now(), status.value(), e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
}
