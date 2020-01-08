package com.demoProject.entities;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="project")
public class Project {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "projectname")
	@NotEmpty(message = "Project Name is empty")
	private String projectName;

	@Column(name = "projectidentifier")
	@Length(min = 5, max = 6, message = "Project identifier needs to be between 5 and 6 characters")
	private String projectIdentifier;

	@Column(name = "description")
	@NotEmpty(message = "description is empty")
	private String description;

	@Column(name = "startdate")
	@NotNull(message = "Start date is empty")
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date startDate;

	@Column(name = "enddate")
	@NotNull(message = "End date is empty")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date endDate;

	@Column(name = "createdat")
	private Date createdAt;

	@Column(name = "updatedat")
	private Date updatedAt;
	
	@OneToOne(
			fetch=FetchType.EAGER,
			cascade=CascadeType.ALL,
			mappedBy="project"
			)
	@JsonIgnore
	private Backlog backlog;

	public Project() {
	}

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
	
	
	

	public Backlog getBacklog() {
		return backlog;
	}

	public void setBacklog(Backlog backlog) {
		this.backlog = backlog;
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
