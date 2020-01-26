package com.demoProject.controllers;

import java.security.Principal;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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
	public Project findByProjectIdnetifier(@Valid @PathVariable String identifier, Principal principal) {

		Project p = projectService.findByIdentifier(identifier, principal.getName());
		return p;

	}

	@GetMapping("")
	public List<Project> findAllProjects(Principal principal) {
		List<Project> p = projectService.findAllProjects(principal.getName());
		return p;
	}

	@PostMapping("")
	public Project save(@Valid @RequestBody Project project, BindingResult result, Principal principal)
			throws Exception {
		System.out.println(project);
		if (result.hasErrors()) {

			throw new RequestHasErrorsException(result.getFieldErrors());

		}

		project.setId(0);
		project.setCreatedAt(getDate());
		project.setUpdatedAt(getDate());

		Project p = projectService.save(project, principal.getName());
		return p;

	}

	@PutMapping("")
	public Project updateProject(@Valid @RequestBody Project project, BindingResult result, Principal principal)
			throws Exception {

		Project oldProject = projectService.findByIdentifier(project.getProjectIdentifier(), principal.getName());
		project.setCreatedAt(oldProject.getCreatedAt());
		project.setUpdatedAt(getDate());
		project.setUser(oldProject.getUser());
		project.setUsername(principal.getName());

		if (result.hasErrors())
			throw new RequestHasErrorsException(result.getFieldErrors());

		try {
			int id = oldProject.getId();
			project.setId(id);

		} catch (Exception e) {
			throw new ProjectNotFoundException();
		}
		Project p = projectService.save(project, principal.getName());
		return p;

	}

	@DeleteMapping("/{identifier}")
	public ResponseEntity<String> deleteByProjectIdnetifier(@Valid @PathVariable String identifier,
			Principal principal) {

		projectService.deleteByIdentifier(identifier, principal.getName());
		return new ResponseEntity<>("project deleted sucessfully", HttpStatus.OK);
	}

	public Date getDate() throws Exception {
		return Date.valueOf(LocalDateTime.now().toLocalDate().toString());

	}

}
