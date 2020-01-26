package com.demoProject.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demoProject.entities.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {
	
	 Project findByProjectIdentifierAndUsername(String identifier,String username);

	List<Project> findByUsername(String username);

}
