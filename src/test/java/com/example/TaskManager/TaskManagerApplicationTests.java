package com.example.TaskManager;

import com.example.TaskManager.controller.TaskController;
import com.example.TaskManager.model.Task;
import com.example.TaskManager.service.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class TaskControllerTest {

	@Mock
	private TaskService taskService;

	@InjectMocks
	private TaskController taskController;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void shouldCreateTask() {
		Task task = new Task();
		when(taskService.createTask(any(Task.class))).thenReturn(task);

		ResponseEntity<Task> response = taskController.createTask(task);

		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals(task, response.getBody());
		verify(taskService, times(1)).createTask(task);
	}

	@Test
	void shouldReturnAllTasks() {
		List<Task> tasks = new ArrayList<>();
		when(taskService.getAllTasks()).thenReturn(tasks);

		ResponseEntity<List<Task>> response = taskController.getAllTasks();

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(tasks, response.getBody());
		verify(taskService, times(1)).getAllTasks();
	}

	@Test
	void shouldReturnTaskById() {
		long taskId = 1L;
		Task task = new Task();
		when(taskService.getTaskById(taskId)).thenReturn(Optional.of(task));

		ResponseEntity<Task> response = taskController.getTaskById(taskId);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(task, response.getBody());
		verify(taskService, times(1)).getTaskById(taskId);
	}

	@Test
	void shouldReturnNotFoundForNonExistingTask() {
		long taskId = 1L;
		when(taskService.getTaskById(taskId)).thenReturn(Optional.empty());

		ResponseEntity<Task> response = taskController.getTaskById(taskId);

		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		verify(taskService, times(1)).getTaskById(taskId);
	}

	@Test
	void shouldUpdateTask() {
		long taskId = 1L;
		Task task = new Task();
		when(taskService.updateTask(any(Task.class))).thenReturn(task);

		ResponseEntity<Task> response = taskController.updateTask(taskId, task);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(task, response.getBody());
		verify(taskService, times(1)).updateTask(task);
	}

	@Test
	void shouldDeleteTaskById() {
		long taskId = 1L;

		ResponseEntity<Void> response = taskController.deleteTask(taskId);

		assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
		verify(taskService, times(1)).deleteTaskById(taskId);
	}
}
