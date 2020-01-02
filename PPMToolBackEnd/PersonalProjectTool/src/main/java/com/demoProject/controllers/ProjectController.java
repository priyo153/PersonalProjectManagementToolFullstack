package com.demoProject.controllers;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demoProject.entities.Project;
import com.demoProject.exceptions.ProjectNotFoundException;
import com.demoProject.exceptions.RequestHasErrorsException;
import com.demoProject.services.ProjectService;

@RestController
@RequestMapping("/projects")
@CrossOrigin
public class ProjectController {

	@Autowired
	ProjectService projectService;

	@GetMapping("/{identifier}")
	public Project findByProjectIdnetifier(@Valid @PathVariable String identifier) {
		
		System.out.println(identifier+"hi");
		Project p = projectService.findByIdentifier(identifier);
		return p;

	}

	@GetMapping("")
	public List<Project> findAllProjects() {
		List<Project> p = projectService.findAllProjects();
		return p;
	}

	@PostMapping("")
	public Project save(@Valid @RequestBody Project project, BindingResult result) throws Exception {
		System.out.println(project);
		if (result.hasErrors()) {

			throw new RequestHasErrorsException(result.getFieldErrors());

		}

		project.setId(0);
		project.setCreatedAt(getDate());
		project.setUpdatedAt(getDate());

		Project p = projectService.save(project);
		return p;

	}

	@PutMapping("")
	public Project updateProject(@Valid @RequestBody Project project, BindingResult result) throws Exception {

		project.setCreatedAt(projectService.findByIdentifier(project.getProjectIdentifier()).getCreatedAt());
		project.setUpdatedAt(getDate());
		if (result.hasErrors())
			throw new RequestHasErrorsException(result.getFieldErrors());

		try {
			int id = projectService.findByIdentifier(project.getProjectIdentifier()).getId();
			project.setId(id);

		} catch (Exception e) {
			throw new ProjectNotFoundException();
		}
		Project p = projectService.save(project);
		return p;

	}

	@DeleteMapping("/{identifier}")
	public ResponseEntity<String> deleteByProjectIdnetifier(@Valid @PathVariable String identifier) {

		projectService.deleteByIdentifier(identifier);
		return new ResponseEntity<>("project deleted sucessfully", HttpStatus.OK);
	}

	public Date getDate() throws Exception {
		return Date.valueOf(LocalDateTime.now().toLocalDate().toString());

	}

}
