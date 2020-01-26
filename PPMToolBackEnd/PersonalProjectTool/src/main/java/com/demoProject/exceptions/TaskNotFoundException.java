package com.demoProject.exceptions;

public class TaskNotFoundException extends RuntimeException {

	public TaskNotFoundException() {
		super("The project task was not found");
	}

}
