package com.roya.blueprint_backend.users;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.roya.blueprint_backend.common.Category;
import com.roya.blueprint_backend.projects.Project;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "BLUEPRINT_USERS")
public class User {
    @Id
    private String userId;
    private String username;
    private String email;
    private String password;
    private String profileUrl;
    private String linkedinUrl;
    @Enumerated(EnumType.STRING)
    private List<Category> interests;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.DETACH, fetch = FetchType.LAZY ,mappedBy = "creator")
    private List<Project> projects;

    @JsonIgnore
    public String getPassword() {
        return password;
    }
}
