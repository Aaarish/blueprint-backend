package com.roya.blueprint_backend.teams;

import com.roya.blueprint_backend.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamMemberDao extends JpaRepository<TeamMember, String> {
    boolean existsByUserAndTeam(User user, Team team);

}
