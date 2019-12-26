package com.demoProject.exceptions;

public class ProjectNotFoundException extends RuntimeException {


	public ProjectNotFoundException() {
		super( "The Project was not found");
	}

	
}
