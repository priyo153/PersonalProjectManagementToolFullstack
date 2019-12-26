package com.demoProject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demoProject.entities.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {
	
	 Project findByProjectIdentifier(String identifier);

}
