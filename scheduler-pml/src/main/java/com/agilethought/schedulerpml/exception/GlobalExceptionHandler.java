package com.agilethought.schedulerpml.exception;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Class for manager standarized responses when a exceptions throws
 * 
 * @author Manuel Ashley Sanchez Zapien <mailto: manuel.zapien>
 */
public class GlobalExceptionHandler {
	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	/**
	 * Method to manage {@link Exception} type
	 * 
	 * @param exception the exception to manage
	 * @return Standarized response
	 */
	@ExceptionHandler(SQLException.class)
	public ResponseEntity<Object> handleException(SQLException exception) {
		logger.error("Unexpected Exception: " + exception.getErrorCode() + exception.getLocalizedMessage(), exception);
		return FormatoRespuesta.generarRespuesta(HttpStatus.INTERNAL_SERVER_ERROR,
				"DataBase problem, contact support");

	}
	/**
	 * Method to manage {@link Exception} type
	 * 
	 * @param exception the exception to manage
	 * @return Standarized response
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleException(Exception exception) {
		logger.error("Unexpected Exception: " + exception.getLocalizedMessage(), exception);
		return FormatoRespuesta.generarRespuesta(HttpStatus.INTERNAL_SERVER_ERROR,
				"Internal error, contact support");

	}
}

/**
 * Class for stanadization of response
 * 
 * @author Manuel Ashley Sanchez Zapien <mailto: manuel.zapien>
 */
class FormatoRespuesta {

	public String message;

	public FormatoRespuesta(String mensaje) {
		super();
		this.message = mensaje;
	}

	static ResponseEntity<Object> generarRespuesta(HttpStatus status, String mensaje) {

		FormatoRespuesta objres = new FormatoRespuesta(mensaje);

		ResponseEntity<Object> respuesta = new ResponseEntity<Object>(objres, status);

		return respuesta;
	}
}