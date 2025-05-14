package com.roya.blueprint_backend.teams;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/teams")
@RequiredArgsConstructor
public class TeamController {
    private final TeamService teamService;

    @PutMapping("/{teamId}/members/{userId}")
    public ResponseEntity<Team> addMemberToTeam (@PathVariable String teamId, @PathVariable String userId) {
        return ResponseEntity.ok(teamService.addMemberToTeam(teamId, userId));
    }

}
