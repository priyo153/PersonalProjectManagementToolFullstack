package com.demoProject.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demoProject.entities.ProjectTask;

@Repository
public interface ProjectTaskRepository extends JpaRepository<ProjectTask, Long> {
	public List<ProjectTask> findByProjectIdentifierOrderByPriority(String id);
	
	public ProjectTask findByProjectSequence(String id);

}
