package com.demoProject.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demoProject.entities.Backlog;

import com.demoProject.entities.ProjectTask;
import com.demoProject.exceptions.TaskNotFoundException;
import com.demoProject.repositories.BacklogRepository;

import com.demoProject.repositories.ProjectTaskRepository;

@Service
public class ProjectTaskService {

	@Autowired
	private BacklogRepository backlogRepository;

	@Autowired
	private ProjectTaskRepository projectTaskRepository;

	public ProjectTask addProjectTask(String projectIdentifier, ProjectTask projectTask) throws Exception {

		Backlog backlog = backlogRepository.findByProjectIdentifier(projectIdentifier);

		projectTask.setBacklog(backlog);
		Integer backlogSequence = backlog.getPTSequence();
		backlogSequence++;
		backlog.setPTSequence(backlogSequence);

		if (projectTask.getId() == null)
			projectTask.setProjectSequence(projectIdentifier + "-" + backlogSequence);

		projectTask.setProjectIdentifier(projectIdentifier);

		if (projectTask.getPriority() == null || projectTask.getPriority() == 0) {
			projectTask.setPriority(3);
		}
		System.out.println(projectTask);
		if (projectTask.getStatus() == "" || projectTask.getStatus() == null) {
			projectTask.setStatus("TO_DO");
		}

		return projectTaskRepository.save(projectTask);

	}

	public List<ProjectTask> findBacklogById(String backlogId) {

		return projectTaskRepository.findByProjectIdentifierOrderByPriority(backlogId);

	}

	public ProjectTask findProjectTask(String id) {
		ProjectTask t = projectTaskRepository.findByProjectSequence(id);
		if (t == null) {
			throw new TaskNotFoundException();
		}

		return projectTaskRepository.findByProjectSequence(id);
	}

	public void deleteProjectTask(ProjectTask p) {
		System.out.println(p);
		projectTaskRepository.delete(p);
		
	}

}
