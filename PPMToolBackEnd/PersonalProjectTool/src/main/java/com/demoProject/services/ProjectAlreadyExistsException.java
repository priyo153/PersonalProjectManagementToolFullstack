package com.demoProject.services;

public class ProjectAlreadyExistsException extends RuntimeException {
	
	ProjectAlreadyExistsException(){
		super("Project already exists");
	}

}
