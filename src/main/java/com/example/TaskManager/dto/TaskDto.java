package com.example.TaskManager.dto;

import com.example.TaskManager.model.Task;

import java.time.LocalDate;

public class TaskDto {
    private final long id;
    private final String title;
    private final String description;
    private final LocalDate dueDate;
    private final boolean completed;

    public TaskDto(long id, String title, String description, LocalDate dueDate, boolean completed) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.completed = completed;
    }

    public TaskDto(Task task) {
        this.id = task.getId();
        this.title = task.getTitle();
        this.description = task.getDescription();
        this.dueDate = task.getDueDate();
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

    public LocalDate getDueDate() {
        return dueDate;
    }

    public boolean isCompleted() {
        return completed;
    }

    public Task toTask() {
        return new Task(this.title, this.description, this.dueDate, this.completed);
    }
}
