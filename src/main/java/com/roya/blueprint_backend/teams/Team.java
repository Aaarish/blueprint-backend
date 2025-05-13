package com.roya.blueprint_backend.teams;

import com.roya.blueprint_backend.projects.Project;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "BLUEPRINT_TEAMS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String projectId;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER ,mappedBy = "team", orphanRemoval = true)
    private List<TeamMember> teamMembers;

}
