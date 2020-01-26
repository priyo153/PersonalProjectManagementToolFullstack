package com.demoProject.exceptions;

public class UserNotFoundException extends RuntimeException {

	
	public UserNotFoundException() {
		super("user was not found");
	}
}
