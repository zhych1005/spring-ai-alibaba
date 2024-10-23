package com.alibaba.cloud.ai.memory.conversation;

import com.alibaba.cloud.ai.memory.client.QwenClient;
import com.alibaba.cloud.ai.memory.context.BaseContextWindowManager;
import com.alibaba.cloud.ai.memory.enums.RoleTypeEnum;
import com.alibaba.dashscope.exception.ApiException;
import com.alibaba.dashscope.exception.InputRequiredException;
import com.alibaba.dashscope.exception.NoApiKeyException;
import org.apache.commons.lang3.StringUtils;

public class QwenContextWindowManager extends BaseContextWindowManager {

    // 生成 Qwen 响应
    public String call(String userInput) throws ApiException, NoApiKeyException, InputRequiredException {
        if (StringUtils.isBlank(apiKey)) {
            throw new IllegalStateException("API key is not set.");
        }
        super.addMemory(RoleTypeEnum.USER.getRoleName(), userInput);
        String response = QwenClient.generateResponse(conversationHistory.history, apiKey, includeSystemPrompt, prompt, modelName, temperature);
        super.addMemory(RoleTypeEnum.ASSISTANT.getRoleName(), response);
        return response;
    }
}