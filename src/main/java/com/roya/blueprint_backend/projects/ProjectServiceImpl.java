package com.roya.blueprint_backend.projects;

import com.roya.blueprint_backend.common.Category;
import com.roya.blueprint_backend.teams.TeamMember;
import com.roya.blueprint_backend.teams.TeamMemberDao;
import com.roya.blueprint_backend.teams.TeamRole;
import com.roya.blueprint_backend.users.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {
    private final ProjectDao projectDao;
    private final ProjectApplicationDao applicationDao;
    private final TeamMemberDao teamMemberDao;

    private static final String PROJECT_NOT_FOUND_EXCEPTION_MESSAGE = "Project not found";

    @Override
    public Project createProject(String title, String description, String categoryString, User user) {
        Category category = Category.fromString(categoryString);
        Project project = new Project(title, description, category, user);

        return projectDao.save(project);
    }

    @Override
    public Project updateProject(String projectId, String title, String description, String category) {
        Project project = projectDao.findById(projectId)
                .orElseThrow(() -> new RuntimeException(PROJECT_NOT_FOUND_EXCEPTION_MESSAGE));

        if (project.getTitle() != null) project.setTitle(title);
        if (project.getDescription() != null) project.setDescription(description);
        if (project.getCategory() != null) project.setCategory(Category.valueOf(category));

        return projectDao.save(project);
    }

    @Override
    public List<TeamMember> fetchProjectMembers(String projectId) {
        Project project = projectDao.findById(projectId)
                .orElseThrow(() -> new RuntimeException(PROJECT_NOT_FOUND_EXCEPTION_MESSAGE));

        return project.getTeam().getTeamMembers();
    }

    @Override
    public ProjectApplication applyForProject(String projectId, String designation, User user) {
        Project project = projectDao.findById(projectId)
                .orElseThrow(() -> new RuntimeException(PROJECT_NOT_FOUND_EXCEPTION_MESSAGE));

        ProjectApplication application = ProjectApplication.builder()
                .project(project)
                .user(user)
                .designation(designation)
                .build();

        project.getApplicants().add(application);
        ProjectApplication savedApplication = applicationDao.save(application);
        projectDao.save(project);

        return savedApplication;
    }

    @Override
    public Project acceptApplication(String projectId, String applicationId) {
        Project project = projectDao.findById(projectId)
                .orElseThrow(() -> new RuntimeException(PROJECT_NOT_FOUND_EXCEPTION_MESSAGE));

        ProjectApplication application = applicationDao.findById(applicationId)
                .orElseThrow(() -> new RuntimeException("Application not found"));

        boolean isAlreadyMember = teamMemberDao.existsByUserAndTeam(
                application.getUser(),
                application.getProject().getTeam()
        );

        if (isAlreadyMember) {
            throw new RuntimeException("User is already a member of the team");
        }

        TeamMember teamMember = TeamMember.builder()
                .role(TeamRole.MEMBER)
                .team(project.getTeam())
                .user(application.getUser())
                .designation(application.getDesignation())
                .build();

        TeamMember savedTeamMember = teamMemberDao.save(teamMember);
        project.getTeam().getTeamMembers().add(savedTeamMember);
        project.getApplicants().remove(application);
        applicationDao.delete(application);

        return projectDao.save(project);
    }
}
