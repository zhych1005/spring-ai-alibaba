package com.alibaba.cloud.ai.memory.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoleTypeEnum {

    USER("user"),
    ASSISTANT("assistant");

    private final String roleName;

    @Override
    public String toString() {
        return roleName;
    }
}