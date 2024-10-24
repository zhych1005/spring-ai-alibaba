package com.alibaba.cloud.ai.memory.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ModelTypeEnum {

    /**
     * qwen model
     */
    QWEN_MAX("qwen-max", 6 * 1024),
    QWEN_MAX_LONGCONTEXT("qwen-max-longcontext", 28 * 1024),
    QWEN_PLUS("qwen-plus", 128 * 1024),
    QWEN_TURBO("qwen-turbo", 6 * 1024);

    private final String modelName;
    private final int contextWindowSize;

    /**
     * 通过modelName获取上下文窗口大小
     * @param modelName 模型名称
     * @return 上下文窗口大小
     */
    public static int getContextWindowSize(String modelName) {
        for (ModelTypeEnum modelTypeEnum : ModelTypeEnum.values()) {
            if (modelTypeEnum.getModelName().equals(modelName)) {
                return modelTypeEnum.getContextWindowSize();
            }
        }
        throw new IllegalArgumentException("Invalid model name: " + modelName);
    }
}