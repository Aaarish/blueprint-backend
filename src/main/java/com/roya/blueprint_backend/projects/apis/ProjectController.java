package com.roya.blueprint_backend.projects.apis;

import com.roya.blueprint_backend.auth.service.AuthService;
import com.roya.blueprint_backend.projects.Project;
import com.roya.blueprint_backend.projects.ProjectApplication;
import com.roya.blueprint_backend.projects.ProjectCreateRequest;
import com.roya.blueprint_backend.projects.ProjectService;
import com.roya.blueprint_backend.teams.TeamMember;
import com.roya.blueprint_backend.users.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;
    private final AuthService authService;

    @PostMapping
    public ResponseEntity<Project> createProject (@RequestBody ProjectCreateRequest projectCreateRequest) {
        User authenticatedUser = authService.getAuthenticatedUser();

        return ResponseEntity.ok(
                projectService.createProject(
                        projectCreateRequest.getTitle(),
                        projectCreateRequest.getDescription(),
                        projectCreateRequest.getCategory(),
                        authenticatedUser));
    }

    @PutMapping("/{projectId}")
    public ResponseEntity<Project> updateProject (@PathVariable String projectId,
                                                  @RequestBody ProjectCreateRequest projectCreateRequest) {

        return ResponseEntity.ok(
                projectService.updateProject(
                        projectId,
                        projectCreateRequest.getTitle(),
                        projectCreateRequest.getDescription(),
                        projectCreateRequest.getCategory()));
    }

    @GetMapping("/{projectId}/members")
    public ResponseEntity<List<TeamMember>> getProjectTeamMembers (@PathVariable String projectId) {
        return ResponseEntity.ok(projectService.fetchProjectMembers(projectId));
    }

    @PostMapping("/{projectId}/application")
    public ResponseEntity<ProjectApplication> applyForProject (@PathVariable String projectId,
                                                               @RequestParam String designation) {
        User authenticatedUser = authService.getAuthenticatedUser();

        return ResponseEntity.ok(
                projectService.applyForProject(projectId, designation, authenticatedUser)
        );
    }

    @PutMapping("/{projectId}/application/{applicationId}")
    public ResponseEntity<Project> acceptApplication (@PathVariable String projectId, @PathVariable String applicationId) {
        return ResponseEntity.ok(projectService.acceptApplication(projectId, applicationId));
    }

}
