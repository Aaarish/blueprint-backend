package com.roya.blueprint_backend.projects;

import com.roya.blueprint_backend.common.Category;
import com.roya.blueprint_backend.users.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {
    private final ProjectDao projectDao;

    @Override
    public Project createProject(String title, String description, String categoryString, User user) {
        Category category = Category.fromString(categoryString);
        Project project = new Project(title, description, category, user);

        return projectDao.save(project);
    }

    @Override
    public Project updateProject(String projectId, String title, String description, String category, User user) {
        return null;
    }
}
