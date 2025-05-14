package com.roya.blueprint_backend.teams;

public interface TeamService {
    Team addMemberToTeam (String teamId, String userId);
}
