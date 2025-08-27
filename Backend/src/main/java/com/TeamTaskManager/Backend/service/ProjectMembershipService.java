package com.TeamTaskManager.Backend.service;

import com.TeamTaskManager.Backend.entity.Project;
import com.TeamTaskManager.Backend.entity.ProjectMembership;
import com.TeamTaskManager.Backend.entity.Role;
import com.TeamTaskManager.Backend.entity.User;
import com.TeamTaskManager.Backend.repository.ProjectMembershipRepository;
import com.TeamTaskManager.Backend.repository.ProjectRepository;
import com.TeamTaskManager.Backend.repository.UserRepository;
import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProjectMembershipService {
    @Autowired
    private ProjectMembershipRepository projectMembershipRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProjectRepository projectRepository;

    public ProjectMembership inviteMember(Long projectId, Long userId, Role role){
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException("Project not found"));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        if (projectMembershipRepository.findByProjectAndUser(project, user).isPresent()){
            throw new IllegalArgumentException("User is already a member");
        }

        ProjectMembership membership = ProjectMembership.builder()
                .user(user)
                .project(project)
                .role(role)
                .invitedAt(LocalDateTime.now())
                .build();

        return projectMembershipRepository.save(membership);
    }

    public List<ProjectMembership> getMembersOfProject(Long projectId){
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException("Project not found"));

        return projectMembershipRepository.findByProject(project);
    }
}
