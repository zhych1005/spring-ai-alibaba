package com.alibaba.cloud.ai.memory.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ModelManufacturerEnum {

	QWEN("qwen"),
	OPENAI("openai");

	private final String name;
}