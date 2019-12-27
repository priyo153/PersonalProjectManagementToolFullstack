package com.demoProject.entities;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
public class Project {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@JsonIgnore
	private int id;
	
	@Column(name="projectname")
	private String projectName;
	
	@Column(name="projectidentifier")
	@Length(min=5,max=6, message="Project identifier needs to be between 5 and 6 characters")
	@NotNull(message="Project identifier is empty")
	private String projectIdentifier;
	
	@Column(name="description")
	@NotNull(message="description is empty")
	private String description;
	
	@Column(name="startdate")
	@NotNull(message="Start date is empty")
	private Date startDate;
	
	@Column(name="enddate")
	@NotNull(message="End date is empty")
	private Date endDate;
	
	@Column(name="createdat")
	private Date createdAt;
	
	@Column(name="updatedat")
	private Date updatedAt;
	
	public Project() {}
	
	public Project(String projectName, String projectIdentifier, String description, Date startDate, Date endDate,
			Date createdAt, Date updatedAt) {
		super();
		this.projectName = projectName;
		this.projectIdentifier = projectIdentifier;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectIdentifier() {
		return projectIdentifier;
	}

	public void setProjectIdentifier(String projectIdentifier) {
		this.projectIdentifier = projectIdentifier;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}



	@Override
	public String toString() {
		return "Project [projectName=" + projectName + ", projectIdentifier=" + projectIdentifier + ", description="
				+ description + ", startDate=" + startDate + ", endDate=" + endDate + ", createdAt=" + createdAt
				+ ", updatedAt=" + updatedAt + "]";
	}
	
	
	
	
	
	

}
