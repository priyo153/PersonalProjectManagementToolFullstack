package com.demoProject.services;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demoProject.entities.Project;
import com.demoProject.exceptions.ProjectNotFoundException;
import com.demoProject.repositories.ProjectRepository;

@Service
public class ProjectService {
	
	@Autowired
	ProjectRepository projectRepository;
	
	public Project findByIdentifier(String identifier) {
		
		Project p= projectRepository.findByProjectIdentifier(identifier);
		if(p==null) {
			throw new ProjectNotFoundException();
		}
	
		return p;
		
		
	}
	
	public List<Project> findAllProjects() {
		List<Project> p=projectRepository.findAll();
		return p;
	}
	
	public Project save(Project project) {
		Project p=projectRepository.save(project);
		return p;
	}

	public void deleteByIdentifier(@Valid String identifier) {
		Project p=findByIdentifier(identifier);
		projectRepository.delete(p);
		
	}
	

}
