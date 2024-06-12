package com.example.TaskManager.service;

import com.example.TaskManager.model.Task;
import com.example.TaskManager.repository.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

	private final TaskRepo taskRepo;

	@Autowired
	public TaskServiceImpl(TaskRepo taskRepo) {
		this.taskRepo = taskRepo;
	}

	@Override
	public Task createTask(Task task) {
		return taskRepo.save(task);
	}

	@Override
	public List<Task> getAllTasks() {
		return taskRepo.findAll();
	}

	@Override
	public Optional<Task> getTaskById(long taskId) {
		return taskRepo.findById(taskId);
	}

	@Override
	public Task updateTask(Task task) {
		Optional<Task> optionalExistingTask = taskRepo.findById(task.getId());

		if (optionalExistingTask.isPresent()) {
			Task existingTask = optionalExistingTask.get();
			existingTask.setTitle(task.getTitle());
			existingTask.setDescription(task.getDescription());
			existingTask.setDueDate(task.getDueDate());
			existingTask.setCompleted(task.isCompleted());
			return taskRepo.save(existingTask);
		} else {
			throw new RuntimeException("Task not found with id: " + task.getId());
		}
	}

	@Override
	public void deleteTaskById(long taskId) {
		taskRepo.deleteById(taskId);
	}

	@Override
	public List<Task> findByTitleLike(String titleLike) {
		return taskRepo.findByTitleContainingIgnoreCase(titleLike);
	}

	@Override
	public List<Task> findCompletedBefore(Boolean completed, LocalDate beforeDueDate) {
		return taskRepo.findByCompletedAndDueDateBefore(completed, beforeDueDate);
	}
}
