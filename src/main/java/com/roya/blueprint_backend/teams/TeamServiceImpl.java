package com.roya.blueprint_backend.teams;

import com.roya.blueprint_backend.users.User;
import com.roya.blueprint_backend.users.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {
    private final TeamDao teamDao;
    private final UserRepository userDao;

    @Override
    public Team addMemberToTeam(String teamId, String userId) {
        Team team = teamDao.findById(teamId)
                .orElseThrow(() -> new RuntimeException("Team not found"));

        User user = userDao.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        TeamMember teamMember = TeamMember.builder()
                .team(team)
                .role(TeamRole.MEMBER)
                .user(user)
                .build();

        team.getTeamMembers().add(teamMember);
        return teamDao.save(team);
    }

}
