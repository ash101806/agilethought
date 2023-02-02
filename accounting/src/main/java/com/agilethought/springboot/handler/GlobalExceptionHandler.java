package com.agilethought.springboot.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.agilethought.springboot.error.BusinessException;

@RestControllerAdvice
public class GlobalExceptionHandler {
	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<Object> handleBusiness (BusinessException  exception){
		
		return FormatoRespuesta.generarRespuesta(exception.getStatus(), exception.getMessage());
		
	}
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleException (Exception  exception){
		logger.error("Error no controlado: " + exception.getLocalizedMessage(),exception );
		return FormatoRespuesta.generarRespuesta(org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR, "Internal error, contact support");
		
	}
}
class FormatoRespuesta {
	
	public String message;
	
	public FormatoRespuesta( String mensaje) {
		super();
		this.message = mensaje;
	}

	static ResponseEntity<Object> generarRespuesta(HttpStatus status, String mensaje) {
		
		FormatoRespuesta objres = new FormatoRespuesta( mensaje);
		
		ResponseEntity<Object> respuesta = new ResponseEntity<Object>(objres, status);
		
		return respuesta;
		
	}
}
