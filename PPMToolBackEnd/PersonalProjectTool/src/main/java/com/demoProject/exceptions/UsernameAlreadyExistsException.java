package com.demoProject.exceptions;

public class UsernameAlreadyExistsException extends RuntimeException {

	public UsernameAlreadyExistsException() {
		super("User already exists");
	}

}
