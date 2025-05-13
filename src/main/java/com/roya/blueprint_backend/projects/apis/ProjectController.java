package com.roya.blueprint_backend.projects.apis;

import com.roya.blueprint_backend.auth.model.AuthUser;
import com.roya.blueprint_backend.auth.service.AuthService;
import com.roya.blueprint_backend.projects.Project;
import com.roya.blueprint_backend.projects.ProjectCreateRequest;
import com.roya.blueprint_backend.projects.ProjectService;
import com.roya.blueprint_backend.users.User;
import com.roya.blueprint_backend.users.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

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

}
