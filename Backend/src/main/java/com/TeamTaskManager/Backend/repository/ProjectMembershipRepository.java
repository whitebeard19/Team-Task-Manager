package com.TeamTaskManager.Backend.repository;

import com.TeamTaskManager.Backend.entity.Project;
import com.TeamTaskManager.Backend.entity.ProjectMembership;
import com.TeamTaskManager.Backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectMembershipRepository extends JpaRepository<ProjectMembership, Long> {
    List<ProjectMembership> findByUser(User user);
    List<ProjectMembership> findByProject(Project project);
    Optional<ProjectMembership> findByProjectAndUser(Project project, User user);
}
