package com.TeamTaskManager.Backend.repository;

import com.TeamTaskManager.Backend.entity.Project;
import com.TeamTaskManager.Backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findByAdmin(User admin);
}
