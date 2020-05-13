package br.com.desafio.jokenpo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

import br.com.desafio.jokenpo.dto.response.DefaultErrorResponse;

@ControllerAdvice
public class GameExceptionHandler {

	@ExceptionHandler(ResponseStatusException.class)
	public ResponseEntity<DefaultErrorResponse> handleException(ResponseStatusException e) {
		DefaultErrorResponse error = new DefaultErrorResponse();
		error.setCode(e.getStatus().value());
		error.setMessage(e.getReason());
		return new ResponseEntity<>(error, e.getStatus());
	}   
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<DefaultErrorResponse> handleException(Exception e) {
		DefaultErrorResponse error = new DefaultErrorResponse();
		error.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		error.setMessage(HttpStatus.INTERNAL_SERVER_ERROR.name());
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
