package com.demoProject.exceptions;

import java.util.List;

import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

public class RequestHasErrorsException extends RuntimeException {
	List<FieldError> fieldErrrorList;

	public RequestHasErrorsException(List<FieldError> e) {
		fieldErrrorList = e;

	}

	public List<FieldError> getFieldErrrorList() {
		return fieldErrrorList;
	}
	
	
	

}
