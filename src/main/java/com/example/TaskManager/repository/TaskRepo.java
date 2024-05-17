package com.example.TaskManager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.TaskManager.model.Task;
import org.springframework.data.jpa.repository.Query;

public interface TaskRepo extends JpaRepository<Task, Long>{}