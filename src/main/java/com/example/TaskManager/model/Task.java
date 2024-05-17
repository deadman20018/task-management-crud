package com.example.TaskManager.model;

import java.time.LocalDate;

import jakarta.persistence.*;


@Entity
@Table(name="tasks")
public class Task {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private long id;
	@Column(name="title")
	private String title;
	@Column(name="description")
	private String description;
	@Column(name="due_date")
	private LocalDate dueDate;
	@Column(name="completed")
	private boolean completed;

	public Task(){}

	public Task(long id, String title, String description, LocalDate dueDate, boolean completed) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.dueDate = dueDate;
		this.completed = completed;
	}


	public long getId() {
		return id;
	}	

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}


	public void setId(long id) {
		this.id = id;
	}
}
