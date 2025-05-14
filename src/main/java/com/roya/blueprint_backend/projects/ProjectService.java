package com.roya.blueprint_backend.projects;

import com.roya.blueprint_backend.teams.TeamMember;
import com.roya.blueprint_backend.users.User;

import java.util.List;

public interface ProjectService {
    Project createProject(String title, String description, String category, User user);
    Project updateProject (String projectId, String title, String description, String category);
    List<TeamMember> fetchProjectMembers(String projectId);
    ProjectApplication applyForProject(String projectId, String designation, User user);
    Project acceptApplication(String projectId, String applicationId);

}
