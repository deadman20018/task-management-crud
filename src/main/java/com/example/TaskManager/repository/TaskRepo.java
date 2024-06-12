package com.example.TaskManager.repository;

import com.example.TaskManager.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TaskRepo extends JpaRepository<Task, Long> {
    List<Task> findByTitleContainingIgnoreCase(String title);

    List<Task> findByCompletedAndDueDateBefore(Boolean completed, LocalDate beforeDueDate);
}
