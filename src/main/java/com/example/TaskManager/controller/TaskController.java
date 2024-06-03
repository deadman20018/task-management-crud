package com.example.TaskManager.controller;

import com.example.TaskManager.dto.TaskDto;
import com.example.TaskManager.model.Task;
import com.example.TaskManager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tasks")
public class TaskController {

	private final TaskService taskService;

	@Autowired
	public TaskController(TaskService taskService) {
		this.taskService = taskService;
	}

	@PostMapping
	public ResponseEntity<TaskDto> createTask(@RequestBody TaskDto dto) {
		Task task = dto.toTask();
		Task createdTask = taskService.createTask(task);
		TaskDto result = new TaskDto(createdTask);
		//return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);
        try {
            URI URI = new URI("/tasks/" + createdTask.getId());
			return ResponseEntity.status(HttpStatus.CREATED).location(URI).body(result);
        } catch (URISyntaxException e) {
            //CANNOT HAPPEN
			return ResponseEntity.internalServerError().build();
        }

	}

	@GetMapping
	public ResponseEntity<List<TaskDto>> searchTasks(@RequestParam (required = false, value = "titleLike") String titleLike,
												  @RequestParam (required = false, value = "completed") Boolean completed,
												  @RequestParam (required = false, value = "beforeDueDate") LocalDate beforeDueDate) {
		List<Task> tasks; // = taskService.getAllTasks();
		if (titleLike != null && !titleLike.isEmpty()) {
			tasks = taskService.findByTitleLike(titleLike);
		} else if (completed != null && beforeDueDate != null) {
			tasks = taskService.findCompletedBefore(completed, beforeDueDate);
		} else {
			tasks = taskService.getAllTasks();
		}
		return ResponseEntity.ok().body(tasks.stream().map(TaskDto::new).toList());
	}

	@GetMapping("/{id}")
	public ResponseEntity<TaskDto> getTaskById(@PathVariable("id") long taskId) {
		Optional<Task> opt = taskService.getTaskById(taskId);

		/*Optional<TaskDto> optDto = opt.map(TaskDto::new);
		Optional<ResponseEntity<TaskDto>> optRes = optDto.map(ResponseEntity::ok);
		//return optRes.orElseGet(() -> ResponseEntity.notFound().build());
		if (optRes.isPresent()) {
			return optRes.get();
		} else {
			return ResponseEntity.notFound().build();
		}

		 */
		if (opt.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		Task t = opt.get();
		TaskDto dto = new TaskDto(t);
		return ResponseEntity.ok(dto);
		//return opt.map(TaskDto::new).map(ResponseEntity::ok)
		//.orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PutMapping("/{id}")
	public ResponseEntity<Task> updateTask(@PathVariable("id") long taskId, @RequestBody Task task) {
		task.setId(taskId);
		Task updatedTask = taskService.updateTask(task);
		return ResponseEntity.ok(updatedTask);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteTaskById(@PathVariable("id") long taskId) {
		taskService.deleteTaskById(taskId);
		return ResponseEntity.noContent().build();
	}
}
