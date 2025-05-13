package com.roya.blueprint_backend.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Category {
    FRONTEND_DEVELOPMENT("frontend_dev"),
    BACKEND_DEVELOPMENT("backend_dev"),
    WEB_DEVELOPMENT("web_dev"),
    MOBILE_DEVELOPMENT("mobile_dev"),
    BLOCKCHAIN("blockchain"),
    CYBER_SECURITY("cyber_sec"),
    GAME_DEVELOPMENT("game_dev"),
    CLOUD_COMPUTING("cloud"),
    DEVOPS("devops"),
    DATABASE_MANAGEMENT("db_management"),
    NETWORKING("networking"),
    DATA_ANALYSIS("data_analysis"),
    MACHINE_LEARNING("ml"),
    IOT("iot");

    private String categoryName;

    public static Category fromString(String category) {
        for (Category cat : Category.values()) {
            if (cat.categoryName.equalsIgnoreCase(category)) {
                return cat;
            }
        }

        return null;
    }
}
