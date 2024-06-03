package com.example.TaskManager.dto;

import com.example.TaskManager.model.Task;

import java.time.LocalDate;

public class TaskDto {
    private long id;
    private String title;
    private String description;
    private String dueDate;
    private boolean completed;

    public TaskDto() {}

    public TaskDto(Task task) {
        this.id = task.getId();
        this.title = task.getTitle();
        this.description = task.getDescription();
        this.dueDate = task.getDueDate().toString();
        this.completed = task.isCompleted();
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getDueDate() {
        return dueDate;
    }

    public boolean isCompleted() {
        return completed;
    }

    public Task toTask() {
        return new Task(this.id, this.title, this.description, LocalDate.parse(this.dueDate), this.completed);
    }
}
