package com.TeamTaskManager.Backend.controller;

import com.TeamTaskManager.Backend.entity.Status;
import com.TeamTaskManager.Backend.entity.Task;
import com.TeamTaskManager.Backend.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestParam Long ProjectId, @RequestBody Task task, @RequestParam(required = false) Long assignedToId){
        Task created = taskService.createTask(ProjectId, task, assignedToId);

        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<Task>> getTasksByProject(@PathVariable Long projectId){
        List<Task> tasks = taskService.getTasksByProject(projectId);

        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Task>> getTasksByAssignedUser(@PathVariable Long userId){
        List<Task> tasks = taskService.getTasksByAssignedUser(userId);

        return ResponseEntity.ok(tasks);
    }

    @PatchMapping("/{taskId}")
    public ResponseEntity<Task> updateTaskStatus(@PathVariable Long taskId, @RequestParam Status status){
        Task updated = taskService.updateTaskStatus(taskId, status);

        return ResponseEntity.ok(updated);
    }
}
