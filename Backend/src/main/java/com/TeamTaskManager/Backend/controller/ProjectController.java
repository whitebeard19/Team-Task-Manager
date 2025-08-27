package com.TeamTaskManager.Backend.controller;

import com.TeamTaskManager.Backend.entity.Project;
import com.TeamTaskManager.Backend.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PostMapping
    public ResponseEntity<Project> createProject(@RequestBody Project project, @RequestParam Long adminId) {
        Project created = projectService.createProject(project, adminId);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/admin/{adminId}")
    public ResponseEntity<List<Project>> getProjectsByAdmin(@PathVariable Long adminId){
        List<Project> projects = projectService.getProjectsForAdmin(adminId);
        return ResponseEntity.ok(projects);
    }
}
