package com.roya.blueprint_backend.projects;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectApplicationDao extends JpaRepository<ProjectApplication, String> {
}
