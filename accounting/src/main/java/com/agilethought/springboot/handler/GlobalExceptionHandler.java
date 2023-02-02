package com.agilethought.springboot.handler;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.agilethought.springboot.error.BusinessException;
/**
 * Class to manage the errors and log error properly, also standarize a response error by Exception type
 * @author Manuel Ashley Sanchez Zapien <mailto: manuel.zapien>
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	/**
	 * Method to manage {@link ConstraintViolationException} exception
	 * @param exception the exception to manage
	 * @return Standarized response
	 */
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<Object> handleValidationException (ConstraintViolationException  exception){
		StringBuilder mensajeError = new StringBuilder();
		Set<ConstraintViolation<?>> lista = exception.getConstraintViolations();
		
		for (ConstraintViolation<?> fieldError : lista) {
			mensajeError.append(fieldError.getPropertyPath() + ": " + fieldError.getMessage() + " ["+fieldError.getInvalidValue()+"]");
		}
		return FormatoRespuesta.generarRespuesta(org.springframework.http.HttpStatus.BAD_REQUEST, mensajeError.toString());
		
	}
	/**
	 * Method to manage {@link MethodArgumentNotValidException} exception
	 * @param exception the exception to manage
	 * @return Standarized response
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> handleValidationException (MethodArgumentNotValidException  exception){
		StringBuilder mensajeError = new StringBuilder();
		List<FieldError> lista = exception.getBindingResult().getFieldErrors();
		for (FieldError fieldError : lista) {
			mensajeError.append(fieldError.getField() + ": " + fieldError.getDefaultMessage());
		}
		return FormatoRespuesta.generarRespuesta(org.springframework.http.HttpStatus.BAD_REQUEST, mensajeError.toString());
		
	}
	/**
	 * Method to manage {@link BusinessException} exception
	 * @param exception the exception to manage
	 * @return Standarized response
	 */
	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<Object> handleBusiness (BusinessException  exception){
		
		return FormatoRespuesta.generarRespuesta(exception.getStatus(), exception.getMessage());
		
	}
	/**
	 * Method to manage {@link Exception} type 
	 * @param exception the exception to manage
	 * @return Standarized response
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleException (Exception  exception){
		logger.error("Unexpected Exception: " + exception.getLocalizedMessage(),exception );
		return FormatoRespuesta.generarRespuesta(org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR, "Internal error, contact support");
		
	}
}
/**
 * Class for stanadization of response
 * @author Manuel Ashley Sanchez Zapien <mailto: manuel.zapien>
 */
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