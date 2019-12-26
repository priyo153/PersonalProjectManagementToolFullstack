package com.demoProject.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {
	
	@ExceptionHandler
	public ResponseEntity<ExceptionObject> projectNotFound(ProjectNotFoundException p){
		
		ExceptionObject e=new ExceptionObject();
		e.setMessage(p.getMessage());
		e.setErrorCode(404);
		return new ResponseEntity<>(e,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<ExceptionObject> requestHasErrors(RequestHasErrorsException p){
		
		ExceptionObject e=new ExceptionObject();
		e.setMessage(p.getMessage());
		e.setErrorCode(403);
		return new ResponseEntity<>(e,HttpStatus.FORBIDDEN);
	}
	
	@ExceptionHandler
	public ResponseEntity<ExceptionObject> genericError(Exception e){
		
		ExceptionObject obj=new ExceptionObject();
		obj.setMessage("Something went wrong!");
		obj.setErrorCode(400);
		return new ResponseEntity<>(obj,HttpStatus.BAD_REQUEST);
	}

}
