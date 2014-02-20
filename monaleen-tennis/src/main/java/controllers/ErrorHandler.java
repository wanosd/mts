package controllers;

import org.springframework.dao.DataAccessException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandler {

	@ExceptionHandler(DataAccessException.class)
	public String handleDBException(DataAccessException e) {
		System.out.println("DataAccessException is " + e.getClass() + "\n" + e.getLocalizedMessage() + "\n" + e.getMessage() + "\n" + e.getStackTrace());
		return "error";
	}
	
	@ExceptionHandler(AccessDeniedException.class)
	public String handleAccessException(AccessDeniedException e) {
		System.out.println(e.getCause() + " @@@@ " + e.getClass());
		return "error";
	}
	
	@ExceptionHandler(Exception.class)
	public String handleOtherException(Exception e){
		return "error";
	}
}
