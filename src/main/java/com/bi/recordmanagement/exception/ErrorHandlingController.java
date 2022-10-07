package com.bi.recordmanagement.exception;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.bi.recordmanagement.enums.RmExceptionEnum;
import com.bi.recordmangement.services.MessageByLocaleService;
import com.bi.recordmanagement.exception.ExceptionResponse;



@ControllerAdvice
@PropertySource("classpath:message.properties")
public class ErrorHandlingController extends ResponseEntityExceptionHandler {

	private static final Logger LFS_LOGGER = LoggerFactory.getLogger(ErrorHandlingController.class);
	@Autowired
	HttpServletRequest httpRequest;
	@Autowired
	MessageByLocaleService messageLocaleService;
	
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<ExceptionResponse> specialException(EntityNotFoundException e) {
		
		LFS_LOGGER.error("EntityNotFoundException occur", e);	
		ExceptionResponse exResponse = new ExceptionResponse();
		exResponse.setCode(HttpStatus.NOT_FOUND.value());
		exResponse.addErrorMessage(e.getMessage());
		return new ResponseEntity<>(exResponse, HttpStatus.NOT_FOUND);

	}
	
	@ExceptionHandler(UserNotVerifiedException.class)
	public ResponseEntity<ExceptionResponse> specialException(UserNotVerifiedException e) {
		
		LFS_LOGGER.error("UserNotVerifiedException occur", e);	
		ExceptionResponse exResponse = new ExceptionResponse();
		exResponse.setCode(e.getCode());
		exResponse.addErrorMessage(e.getMessage());
		return new ResponseEntity<>(exResponse, HttpStatus.NOT_FOUND);

	}
	
	@ExceptionHandler(BadCredentialException.class)
	public ResponseEntity<ExceptionResponse> specialException(BadCredentialException e) {
		
		LFS_LOGGER.error("BadCredentialException occur", e);	
		ExceptionResponse exResponse = new ExceptionResponse();
		exResponse.setCode(RmExceptionEnum.BAD_CREDENTIALS.getCode());
		exResponse.addErrorMessage(e.getMessage());
		return new ResponseEntity<>(exResponse, HttpStatus.NOT_FOUND);

	}
	
	@ExceptionHandler(RMServiceException.class)
	public ResponseEntity<ExceptionResponse> generalServiceException(RMServiceException e) {
		
		LFS_LOGGER.error("GMServiceException occur", e);	
		ExceptionResponse exResponse = new ExceptionResponse();
		exResponse.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		exResponse.addErrorMessage(e.getMessage());
		return new ResponseEntity<>(exResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(ForbiddenException.class)
	public ResponseEntity<ExceptionResponse> generalServiceException(ForbiddenException e) {
		
		LFS_LOGGER.error("ForbiddenException occur",e);	
		ExceptionResponse exResponse = new ExceptionResponse();
		exResponse.setCode(HttpStatus.FORBIDDEN.value());
		exResponse.addErrorMessage(e.getMessage());
		String currentUrl = httpRequest.getRequestURI();
		exResponse.setPath(currentUrl);
		return new ResponseEntity<>(exResponse, HttpStatus.FORBIDDEN);
	}
	
	@ExceptionHandler(InvalidOTPException.class)
	public ResponseEntity<ExceptionResponse> generalServiceException(InvalidOTPException e) {
		
		LFS_LOGGER.error("InvalidOTPException occur",e);	
		ExceptionResponse exResponse = new ExceptionResponse();
		exResponse.setCode(HttpStatus.UNPROCESSABLE_ENTITY.value());
		exResponse.addErrorMessage(e.getMessage());
		return new ResponseEntity<>(exResponse, HttpStatus.UNPROCESSABLE_ENTITY);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		LFS_LOGGER.error("MethodArgumentNotValidException occur",ex);	
		ExceptionResponse exceptionresponse = new ExceptionResponse();
		BindingResult bindingResult = ex.getBindingResult();
		List<ObjectError> list = bindingResult.getAllErrors();
		String currentUrl = httpRequest.getRequestURI();
		exceptionresponse.setPath(currentUrl);
		for (ObjectError error : list) {
			exceptionresponse.addErrorMessage(error.getDefaultMessage());
		}
		exceptionresponse.setCode(status.value());
		return new ResponseEntity<>(exceptionresponse, HttpStatus.UNPROCESSABLE_ENTITY);
		}
	@ExceptionHandler(ConstraintViolationException.class)
	public final ResponseEntity<ExceptionResponse> handleConstraintViolationException(ConstraintViolationException ex) {
		
		LFS_LOGGER.error("ConstraintViolationException occur",ex);	
		ExceptionResponse exResponse = new ExceptionResponse();
		String currentUrl = httpRequest.getRequestURI();
		exResponse.setPath(currentUrl);
		exResponse.addErrorMessage(ex.getMessage());
		return new ResponseEntity<>(exResponse, HttpStatus.UNPROCESSABLE_ENTITY);
	}
	@ExceptionHandler(DataIntegrityViolationException.class)
	public final ResponseEntity<ExceptionResponse> handleDataIntegrityViolationException(DataIntegrityViolationException ex, WebRequest request) {
		LFS_LOGGER.error("DataIntegrityViolationException occur",ex);	
		ExceptionResponse exResponse = new ExceptionResponse();
			String currentUrl = httpRequest.getRequestURI();
			exResponse.setPath(currentUrl);
			Pattern pattern = Pattern.compile(".*CONSTRAINT `(.*)` FOREIGN KEY.*");
			if(ex.getCause() instanceof ConstraintViolationException) {
				ConstraintViolationException cVE = (ConstraintViolationException) ex.getCause();
				if(cVE.getConstraintName() == null) {
					String errMessage = cVE.getSQLException().getMessage();
					if(errMessage.contains("foreign key constraint fails")) {
						Matcher mathcher = pattern.matcher(errMessage);
						if(mathcher.matches()) {
							String constraint = mathcher.group(1);
							exResponse.addErrorMessage(messageLocaleService.getMessage(constraint.replace("_",".")));
							request.getDescription(true);
							exResponse.setCode(HttpStatus.UNPROCESSABLE_ENTITY.value());
							return new ResponseEntity<>(exResponse, HttpStatus.UNPROCESSABLE_ENTITY);
						}
					}
				} else {
					exResponse.addErrorMessage(messageLocaleService.getMessage(cVE.getConstraintName().replace("_",".")));
					request.getDescription(true);
					exResponse.setCode(HttpStatus.CONFLICT.value());
					return new ResponseEntity<>(exResponse, HttpStatus.CONFLICT);
				}
				
			}
			
			return null;
	}
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ExceptionResponse> specialException(Exception e) {
		LFS_LOGGER.error("Exception occur",e);
		ExceptionResponse exResponse = new ExceptionResponse();
		exResponse.setCode(500);
		exResponse.addErrorMessage(e.getMessage());
		String currentUrl = httpRequest.getRequestURI();
		exResponse.setPath(currentUrl);
		return new ResponseEntity<>(exResponse, HttpStatus.EXPECTATION_FAILED);
	}
	
	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<ExceptionResponse> handleValidationException(ValidationException ex) throws Exception {
		ExceptionResponse response = new ExceptionResponse();
		response.setCode(HttpStatus.BAD_REQUEST.value());
		response.addErrorMessage(ex.getMessage());
		return new ResponseEntity<ExceptionResponse>(response, HttpStatus.BAD_REQUEST);
	}
}
