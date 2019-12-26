package com.demoProject.exceptions;

import java.util.List;

import org.springframework.validation.ObjectError;

public class RequestHasErrorsException extends RuntimeException {
	
	public RequestHasErrorsException(){
		
		super("the request has errors");
		
	
	}
	

}
