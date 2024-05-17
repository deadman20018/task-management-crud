package com.example.TaskManager.service;

import com.example.TaskManager.model.Task;

import java.util.List;
import java.util.Optional;

public interface TaskService {

	Task createTask(Task task);

	List<Task> getAllTasks();

	Optional<Task> getTaskById(long taskId);

	Task updateTask(Task task);

	void deleteTaskById(long taskId);
}
