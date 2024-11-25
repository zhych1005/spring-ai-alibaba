package com.alibaba.cloud.ai.memory.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public enum ModelTypeEnum {

	/**
	 * qwen model
	 */
	QWEN_MAX("qwen-max", ModelManufacturerEnum.QWEN, 6 * 1024),
	QWEN_MAX_LONGCONTEXT("qwen-max-longcontext", ModelManufacturerEnum.QWEN, 28 * 1024),
	QWEN_PLUS("qwen-plus", ModelManufacturerEnum.QWEN, 128 * 1024), QWEN_TURBO("qwen-turbo", ModelManufacturerEnum.QWEN, 6 * 1024),
	GPT35_TURBO("gpt-3.5-turbo", ModelManufacturerEnum.OPENAI, 6 * 1024), GPT_4("gpt-4", ModelManufacturerEnum.OPENAI, 6 * 1024),
	GPT_4O_MINI("gpt-4o-mini", ModelManufacturerEnum.OPENAI, 6 * 1024);

	private final String modelName;

	private final ModelManufacturerEnum manufacturer;

	private final int contextWindowSize;

	/**
	 * 通过modelName获取模型
	 * @param modelName 模型名称
	 * @return 上下文窗口大小
	 */
	public static ModelTypeEnum getModel(String modelName) {
		for (ModelTypeEnum modelTypeEnum : ModelTypeEnum.values()) {
			if (modelTypeEnum.getModelName().equals(modelName)) {
				return modelTypeEnum;
			}
		}
		throw new IllegalArgumentException("Invalid model name: " + modelName);
	}
}