package com.example.demo.services.excecoes;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.ObjectNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound (ObjectNotFoundException e, HttpServletRequest servlet){
		StandardError err = new StandardError (HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis()); 
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);		
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<StandardError> dataIntegrity (DataIntegrityViolationException e, HttpServletRequest servlet){
		StandardError err = new StandardError (HttpStatus.BAD_REQUEST.value(), e.getMessage(), System.currentTimeMillis()); 
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}

	//Mostra qual exceção vai pegar
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> validation (MethodArgumentNotValidException e, HttpServletRequest servlet){
		ValidationError err = new ValidationError (HttpStatus.BAD_REQUEST.value(), "Erro de validação.", System.currentTimeMillis());
		
		for (FieldError x : e.getBindingResult().getFieldErrors()) 
			err.addError(x.getField(), x.getDefaultMessage());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}	
	
}
