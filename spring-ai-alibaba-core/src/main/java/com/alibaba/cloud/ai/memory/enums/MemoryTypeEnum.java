package com.alibaba.cloud.ai.memory.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.NoSuchElementException;


@Getter
@AllArgsConstructor
public enum MemoryTypeEnum {

	BUFFERWINDOW("bufferwindow");

	private final String name;

	public static MemoryTypeEnum getEnum(String name) {
		return Arrays.stream(MemoryTypeEnum.values())
				.filter(value -> value.getName().equals(name))
				.findFirst()
				.orElseThrow(() -> new NoSuchElementException("No enum constant " + MemoryTypeEnum.class.getName() + "." + name));
	}
}