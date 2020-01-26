package com.demoProject.services;

import java.util.Comparator;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demoProject.entities.Backlog;
import com.demoProject.entities.Project;
import com.demoProject.entities.User;
import com.demoProject.exceptions.ProjectNotFoundException;
import com.demoProject.repositories.BacklogRepository;
import com.demoProject.repositories.ProjectRepository;
import com.demoProject.repositories.UserRepository;

@Service
public class ProjectService {

	@Autowired
	ProjectRepository projectRepository;
	@Autowired
	BacklogRepository backlogRepository;

	@Autowired
	UserRepository userRepository;

	public Project findByIdentifier(String identifier, String username) {

		Project p = projectRepository.findByProjectIdentifierAndUsername(identifier,username);
		if (p == null) {
			throw new ProjectNotFoundException();
		}

		return p;

	}

	public List<Project> findAllProjects(String username) {
		List<Project> p = projectRepository.findByUsername(username);
		p.sort(Comparator.comparing(Project::getId).reversed());
		return p;
	}

	public Project save(Project project, String username) {
		try {
			User user=userRepository.findByUsername(username);
			project.setUser(user);
			project.setUsername(user.getUsername());
			String projectIdentifier = project.getProjectIdentifier().toUpperCase();

			project.setProjectIdentifier(projectIdentifier);
			if (project.getId() == 0) {
				Backlog backlog = new Backlog();
				project.setBacklog(backlog);
				backlog.setProject(project);
				backlog.setProjectIdentifier(projectIdentifier);
			} else {
				project.setBacklog(backlogRepository.findByProjectIdentifier(projectIdentifier));
			}
			Project p = projectRepository.save(project);
			return p;
		} catch (Exception e) {
			throw new ProjectAlreadyExistsException();
		}
	}

	public void deleteByIdentifier(@Valid String identifier,String username) {
		Project p = findByIdentifier(identifier,username);
		projectRepository.delete(p);

	}

}
