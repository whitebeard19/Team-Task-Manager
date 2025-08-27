package com.TeamTaskManager.Backend.service;

import com.TeamTaskManager.Backend.entity.*;
import com.TeamTaskManager.Backend.repository.ProjectRepository;
import com.TeamTaskManager.Backend.repository.TaskRepository;
import com.TeamTaskManager.Backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserRepository userRepository;

    public Task createTask(Long projectId, Task task, Long assignedTo){
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException("Project not found"));

        task.setProject(project);

        if (assignedTo != null){
            User assignedToId = userRepository.findById(assignedTo)
                    .orElseThrow(() -> new IllegalArgumentException("Assigned user not found"));
            task.setAssignedTo(assignedToId);
        }

        task.setPriority(Priority.Low);
        task.setStatus(Status.ToDo);
        task.setDueDate(LocalDateTime.now().plusDays(5));

        return taskRepository.save(task);

    }

    public List<Task> getTasksByProject(Long projectId){
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new IllegalArgumentException("Project not found"));

        return taskRepository.findByProject(project);
    }

    public List<Task> getTasksByAssignedUser(Long userId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        return taskRepository.findByAssignedTo(user);
    }

    public Task updateTaskStatus(Long taskId, Status status){
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new IllegalArgumentException("Task not found"));

        task.setStatus(status);
        task.setUpdatedAt(LocalDateTime.now());

        return taskRepository.save(task);
    }
}
