package com.roya.blueprint_backend.projects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.roya.blueprint_backend.common.Category;
import com.roya.blueprint_backend.teams.Team;
import com.roya.blueprint_backend.teams.TeamMember;
import com.roya.blueprint_backend.teams.TeamRole;
import com.roya.blueprint_backend.users.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "BLUEPRINT_PROJECTS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Project {
    @Id
    private String id;
    private String title;
    private String description;
    @Enumerated(EnumType.STRING)
    private Category category;
    private Date createdAt;

    @ManyToOne
    @JoinColumn(name = "creator_id", referencedColumnName = "userId", nullable = false)
    private User creator;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "team_id", referencedColumnName = "id", nullable = false)
    private Team team;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY ,orphanRemoval = true, mappedBy = "project")
    private List<ProjectApplication> applicants;

    public Project(String title, String description, Category category, User creator) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.description = description;
        this.category = category;
        this.creator = creator;
        this.createdAt = new Date();
        this.applicants = new ArrayList<>();
        this.team = Team.builder()
                .projectId(this.getId())
                .teamMembers(new ArrayList<>())
                .build();
        this.team.getTeamMembers().add(TeamMember.builder()
                .role(TeamRole.CREATOR)
                .user(this.getCreator())
                .team(this.getTeam())
                .designation("Project Creator")
                .build());
    }
}
