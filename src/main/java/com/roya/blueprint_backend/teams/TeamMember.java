package com.roya.blueprint_backend.teams;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.roya.blueprint_backend.users.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "BLUEPRINT_TEAM_MEMBERS",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"user_id", "team_id"})
        })
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class TeamMember {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "team_id", referencedColumnName = "id", nullable = false, unique = false)
    private Team team;

    @Enumerated(EnumType.STRING)
    private TeamRole role;
    private String designation;

    @OneToOne
    @JoinColumn(name="user_id", referencedColumnName = "userId", nullable = false, unique = false)
    private User user;

}
