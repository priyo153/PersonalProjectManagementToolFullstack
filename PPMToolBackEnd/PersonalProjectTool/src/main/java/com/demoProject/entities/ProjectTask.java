package com.demoProject.entities;

import java.sql.Date;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="project_task")
public class ProjectTask  {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="project_sequence",updatable=false)
	private String projectSequence;
	
	@NotBlank(message="please include a project summary")
	@Column(name="summary")
	private String summary;
	@NotBlank(message="please include the Acceptance Criteria")
	@Column(name="acceptance_criteria")
	private String acceptanceCriteria;
	@Column(name="status")
	@NotNull(message="please include the status")
	private String status;
	@Column(name="priority")
	private Integer priority;
	@Column(name="due_date")
	@NotNull(message="please include the Due Date")
	private Date dueDate;
	@Column(name="project_identifier")
	private String projectIdentifier;
	
	@Column(name="created_at")
	private Date createdAt;
	@Column(name="updated_at")
	private Date updatedAt;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="backlog_id",updatable=false,nullable=false)
	@JsonIgnore
	private Backlog backlog;
	
	ProjectTask(){}
	
	

	public Backlog getBacklog() {
		return backlog;
	}



	public void setBacklog(Backlog backlog) {
		this.backlog = backlog;
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getProjectSequence() {
		return projectSequence;
	}



	public void setProjectSequence(String projectSequence) {
		this.projectSequence = projectSequence;
	}



	public String getSummary() {
		return summary;
	}



	public void setSummary(String summary) {
		this.summary = summary;
	}



	public String getAcceptanceCriteria() {
		return acceptanceCriteria;
	}



	public void setAcceptanceCriteria(String acceptanceCriteria) {
		this.acceptanceCriteria = acceptanceCriteria;
	}



	public String getStatus() {
		return status;
	}



	public void setStatus(String status) {
		this.status = status;
	}



	public Integer getPriority() {
		return priority;
	}



	public void setPriority(Integer priority) {
		this.priority = priority;
	}



	public Date getDueDate() {
		return dueDate;
	}



	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}



	public String getProjectIdentifier() {
		return projectIdentifier;
	}



	public void setProjectIdentifier(String projectIdentifier) {
		this.projectIdentifier = projectIdentifier;
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



	@PrePersist
	protected void onCreate() {
		this.createdAt=Date.valueOf(LocalDateTime.now().toLocalDate().toString());
		this.updatedAt=Date.valueOf(LocalDateTime.now().toLocalDate().toString());
	}
	
	@PreUpdate
	protected void onUpdate() {
		this.updatedAt=Date.valueOf(LocalDateTime.now().toLocalDate().toString());
		
	}



	@Override
	public String toString() {
		return "ProjectTask [id=" + id + ", projectSequence=" + projectSequence + ", summary=" + summary
				+ ", acceptanceCriteria=" + acceptanceCriteria + ", status=" + status + ", priority=" + priority
				+ ", dueDate=" + dueDate + ", projectIdentifier=" + projectIdentifier + ", createdAt=" + createdAt
				+ ", updatedAt=" + updatedAt + "]";
	}
	
	 
	

}
