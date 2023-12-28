package com.erpak.barter.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {

    USER_READ("user::read"),
    USER_UPDATE("user::update"),
    USER_CREATE("user::create"),
    USER_DELETE("user::delete"),

    ADMIN_READ("admin::read"),
    ADMIN_UPDATE("admin::update"),
    ADMIN_CREATE("admin::create"),
    ADMIN_DELETE("admin::delete"),

    MANAGER_READ("manager::read"),
    MANAGER_UPDATE("manager::update"),
    MANAGER_CREATE("manager::create"),
    MANAGER_DELETE("manager::delete");

    @Getter
    private final String permission;
}
