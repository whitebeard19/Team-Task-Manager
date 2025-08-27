package com.TeamTaskManager.Backend.controller;

import com.TeamTaskManager.Backend.entity.ProjectMembership;
import com.TeamTaskManager.Backend.entity.Role;
import com.TeamTaskManager.Backend.repository.ProjectMembershipRepository;
import com.TeamTaskManager.Backend.service.ProjectMembershipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projectmemberships")
public class ProjectMembershipController {

    @Autowired
    private ProjectMembershipService projectMembershipService;

    @PostMapping("/invite")
    public ResponseEntity<ProjectMembership> inviteMember(@RequestParam
                                                           Long projectId, @RequestParam Long userId, @RequestParam Role role){
        ProjectMembership membership = projectMembershipService.inviteMember(projectId, userId, role);

        return new ResponseEntity<>(membership, HttpStatus.CREATED);
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<ProjectMembership>> getMembersByProject(@PathVariable Long projectId){
        List<ProjectMembership> members = projectMembershipService.getMembersOfProject(projectId);

        return new ResponseEntity<>(members, HttpStatus.OK);
    }


}
