package com.TeamTaskManager.Backend.service;

import com.TeamTaskManager.Backend.entity.Project;
import com.TeamTaskManager.Backend.entity.User;
import com.TeamTaskManager.Backend.repository.ProjectMembershipRepository;
import com.TeamTaskManager.Backend.repository.ProjectRepository;
import com.TeamTaskManager.Backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProjectRepository projectRepository;

    public Project createProject(Project project, Long adminId){
        User admin = userRepository.findById(adminId)
                .orElseThrow(()-> new IllegalArgumentException("Admin not found"));
        project.setAdmin(admin);
        return projectRepository.save(project);

    }

    public List<Project> getProjectsForAdmin(Long adminId) {
        User admin = userRepository.findById(adminId)
                .orElseThrow(() -> new IllegalArgumentException("Admin not found"));

        return projectRepository.findByAdmin(admin);

    }
}
