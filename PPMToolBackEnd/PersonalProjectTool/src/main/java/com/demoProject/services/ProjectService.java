package com.demoProject.services;

import java.util.Comparator;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demoProject.entities.Backlog;
import com.demoProject.entities.Project;
import com.demoProject.exceptions.ProjectNotFoundException;
import com.demoProject.repositories.BacklogRepository;
import com.demoProject.repositories.ProjectRepository;

@Service
public class ProjectService {
	
	@Autowired
	ProjectRepository projectRepository;
	@Autowired
	BacklogRepository backlogRepository;
	
	public Project findByIdentifier(String identifier) {
		
		Project p= projectRepository.findByProjectIdentifier(identifier);
		if(p==null) {
			throw new ProjectNotFoundException();
		}
	
		return p;
		
		
	}
	
	public List<Project> findAllProjects() {
		List<Project> p=projectRepository.findAll();
		p.sort(Comparator.comparing(Project::getId).reversed());
		return p;
	}
	
	public Project save(Project project) {
		String projectIdentifier = project.getProjectIdentifier().toUpperCase();
		
		project.setProjectIdentifier(projectIdentifier);
		if(project.getId()==0) {
			Backlog backlog=new Backlog();
			project.setBacklog(backlog);
			backlog.setProject(project);
			backlog.setProjectIdentifier(projectIdentifier);
		}
		else {
			project.setBacklog(backlogRepository.findByProjectIdentifier(projectIdentifier));
		}
		Project p=projectRepository.save(project);
		return p;
	}

	public void deleteByIdentifier(@Valid String identifier) {
		Project p=findByIdentifier(identifier);
		projectRepository.delete(p);
		
	}
	

}
