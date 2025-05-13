package com.roya.blueprint_backend.projects;

import lombok.Builder;
import lombok.Getter;

@Builder
public record ProjectCreateRequest (@Getter String title,@Getter String description,@Getter String category) {
}
