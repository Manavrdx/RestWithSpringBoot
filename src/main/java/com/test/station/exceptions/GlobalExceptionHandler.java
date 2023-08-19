package com.test.station.exceptions;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler({MethodArgumentNotValidException.class, BindException.class})
	protected ResponseEntity<?> handleMethodArgumentNotValid(BindException ex,
															 WebRequest request) {
		ErrorResponse errorResponse =
				new ErrorResponse(new Date(), HttpStatus.UNPROCESSABLE_ENTITY.value(),
						"Validation error. Check 'errors' field for details.",
						request.getDescription(false));

		for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
			errorResponse.addValidationError(fieldError.getField(), fieldError.getDefaultMessage());
		}

		LOGGER.error("BindException :: ", ex);
		return ResponseEntity.unprocessableEntity().body(errorResponse);
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> resourceNotFoundHandling(ResourceNotFoundException exception, WebRequest request){
		ErrorResponse errorResponse =
				new ErrorResponse(new Date(), HttpStatus.NOT_FOUND.value(), exception.getMessage(), request.getDescription(false));
		LOGGER.error("Exception:: "+exception);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<?> illegalArgumentHandling(IllegalArgumentException exception, WebRequest request){
		ErrorResponse errorResponse =
				new ErrorResponse(new Date(), HttpStatus.BAD_REQUEST.value(), exception.getMessage(),
						request.getDescription(false));
		LOGGER.error("IllegalArgumentException ::", exception);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<?> handleDataIntegrityViolationException(DataIntegrityViolationException exception,
																   WebRequest request){
		LOGGER.info("DataIntegrityViolationException occurred:: "+exception);
		ErrorResponse errorResponse =
				new ErrorResponse(new Date(), HttpStatus.CONFLICT.value(),
						"Some constraints violated at Database end, " +
								"Please check the data you have provided is correct..!",
						request.getDescription(false));

		return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
	}

	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<?> nullPointerHandling(NullPointerException exception, WebRequest request){
		ErrorResponse errorResponse =
				new ErrorResponse(new Date(), HttpStatus.INTERNAL_SERVER_ERROR.value(),
						"Expecting value found null",
						request.getDescription(false));
		LOGGER.error("Internal System Exception NullPointerException ::", exception);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
	}

	@ExceptionHandler(GeneralBadRequestException.class)
	public ResponseEntity<?> invalidInputExceptionHandler(GeneralBadRequestException exception,
                                                          WebRequest webRequest){
		ErrorResponse errorResponse =
				new ErrorResponse(new Date(), HttpStatus.BAD_REQUEST.value(),
						exception.getMessage(),
						webRequest.getDescription(false));
		LOGGER.error("Bad Request Exception:: "+exception);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> globalExceptionHandling(Exception exception, WebRequest request){

		Throwable throwable = ExceptionUtils.getRootCause(exception);
		if(throwable != null) {
			if(throwable instanceof IllegalArgumentException) {
				return illegalArgumentHandling((IllegalArgumentException) throwable, request);
			}
		}

		ErrorResponse errorResponse =
				new ErrorResponse(new Date(), HttpStatus.INTERNAL_SERVER_ERROR.value(),
						exception.getMessage(), request.getDescription(false));
		LOGGER.error("Internal System Exception ::", exception);
		return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}