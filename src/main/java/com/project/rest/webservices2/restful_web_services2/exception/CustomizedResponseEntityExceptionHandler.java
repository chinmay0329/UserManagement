package com.project.rest.webservices2.restful_web_services2.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.project.rest.webservices2.restful_web_services2.users.UserNotFoundException;

@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler 
		extends ResponseEntityExceptionHandler
{
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) throws Exception
	{
		ErrorDetails error=new ErrorDetails(LocalDateTime.now(),
							ex.getMessage(), request.getDescription(false));
		
		return new ResponseEntity<Object>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	// now even when user is not found it will give
	// 500 instead of 404 not found
	// so now we will again customize the above method
	
	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<Object> handleUserNotFoundException(Exception ex, WebRequest request) throws Exception
	{
		ErrorDetails error=new ErrorDetails(LocalDateTime.now(),
							ex.getMessage(), request.getDescription(false));
		
		return new ResponseEntity<Object>(error, HttpStatus.NOT_FOUND);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, 
			HttpStatusCode status, WebRequest request) 
	{
//		ErrorDetails error=new ErrorDetails(LocalDateTime.now(),
//						   ex.getMessage(), request.getDescription(false));
		
		// now in place of ex.getMessage , we will put our own message
		
		ErrorDetails error=new ErrorDetails(LocalDateTime.now(),
				   "Error Count: "+ex.getErrorCount()+" First Error: "+ex.getFieldError().getDefaultMessage(), request.getDescription(false));

		return new ResponseEntity<Object>(error, HttpStatus.BAD_REQUEST);
	}
		
}
