package com.example.task.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.task.entity.Task;
import com.example.task.repository.TaskRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

	@Autowired
	private final TaskRepository taskRepository;

	public TaskService(TaskRepository taskRepository) {
		this.taskRepository = taskRepository;
	}

	public List<Task> getAllTasks() {
		return taskRepository.findAll();
	}

	public Optional<Task> getTaskById(Long id) {
		return taskRepository.findById(id);
	}

	public Task createTask(Task task) {
		return taskRepository.save(task);
	}

	public Optional<Task> updateTask(Long id, Task updatedTask) {
		return taskRepository.findById(id).map(existingTask -> {
			existingTask.setTitle(updatedTask.getTitle());
			existingTask.setDescription(updatedTask.getDescription());
			return taskRepository.save(existingTask);
		});
	}

	public boolean deleteTask(Long id) {
		if (taskRepository.existsById(id)) {
			taskRepository.deleteById(id);
			return true;
		}
		return false;
	}
}
