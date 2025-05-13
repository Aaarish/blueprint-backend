package com.roya.blueprint_backend.teams;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.roya.blueprint_backend.users.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "BLUEPRINT_TEAM_MEMBERS")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class TeamMember {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "team_id", referencedColumnName = "id", nullable = false)
    private Team team;

    @Enumerated(EnumType.STRING)
    private TeamRole role;

    @OneToOne
    @JoinColumn(name="user_id", referencedColumnName = "userId", nullable = false)
    private User user;

}
