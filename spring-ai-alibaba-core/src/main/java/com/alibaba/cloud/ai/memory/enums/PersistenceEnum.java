package com.alibaba.cloud.ai.memory.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.NoSuchElementException;

@Getter
@AllArgsConstructor
public enum PersistenceEnum {
    MYSQL("mysql"),
    REDIS("redis"),
    ES("es");

    private final String name;

    public static PersistenceEnum getEnum(String name) {
        return Arrays.stream(PersistenceEnum.values())
                .filter(value -> value.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("No enum constant " + PersistenceEnum.class.getName() + "." + name));
    }
}
