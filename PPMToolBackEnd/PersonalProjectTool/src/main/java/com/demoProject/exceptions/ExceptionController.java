package com.demoProject.exceptions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;

@ControllerAdvice
public class ExceptionController {

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		StringTrimmerEditor stringtrimmer = new StringTrimmerEditor(true);
		binder.registerCustomEditor(String.class, stringtrimmer);
	}

	@ExceptionHandler
	public ResponseEntity<ExceptionObject> projectNotFound(ProjectNotFoundException p) {

		ExceptionObject e = new ExceptionObject();
		e.setMessage(p.getMessage());
		e.setErrorCode(404);
		return new ResponseEntity<>(e, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler
	public ResponseEntity<Map<String, String>> requestHasErrors(RequestHasErrorsException e) {

		List<FieldError> fieldErrrorList = e.getFieldErrrorList();
		Map<String, String> errors = new HashMap<>();
		for (FieldError f : fieldErrrorList) {
			errors.put(f.getField(), f.getDefaultMessage());
		}
		return new ResponseEntity<Map<String, String>>(errors, HttpStatus.FORBIDDEN);
	}

	@ExceptionHandler
	public ResponseEntity<ExceptionObject> genericError(Exception e) {

		ExceptionObject obj = new ExceptionObject();
		e.printStackTrace();
		obj.setMessage("Something went wrong!");
		obj.setErrorCode(400);
		return new ResponseEntity<>(obj, HttpStatus.BAD_REQUEST);
	}

}
