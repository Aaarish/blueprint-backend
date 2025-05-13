package com.roya.blueprint_backend.projects;

import com.roya.blueprint_backend.common.Category;
import com.roya.blueprint_backend.users.User;

public interface ProjectService {
    Project createProject(String title, String description, String category, User user);
    Project updateProject (String projectId, String title, String description, String category, User user);
}
