package com.alibaba.cloud.ai.memory.client;

import com.alibaba.cloud.ai.memory.history.ConversationHistory;
import com.alibaba.dashscope.aigc.generation.Generation;
import com.alibaba.dashscope.aigc.generation.GenerationParam;
import com.alibaba.dashscope.aigc.generation.GenerationResult;
import com.alibaba.dashscope.common.Message;
import com.alibaba.dashscope.common.Role;
import com.alibaba.dashscope.exception.ApiException;
import com.alibaba.dashscope.exception.InputRequiredException;
import com.alibaba.dashscope.exception.NoApiKeyException;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class QwenClient {

    private QwenClient(){}

    protected static LinkedList<Message> messageHistory = new LinkedList<>();

    private static final Generation gen = new Generation();

    // 生成 Qwen 响应
    public static String generateResponse(List<ConversationHistory.Message> messages, String apiKey, boolean includeSystemPrompt, String prompt, String modelName, float temperature) throws ApiException, NoApiKeyException, InputRequiredException {
        // 创建生成参数并调用 API
        GenerationParam param = createGenerationParam(messages, apiKey, includeSystemPrompt, prompt, modelName, temperature);
        GenerationResult result = gen.call(param);
        // 获取模型响应
        return result.getOutput().getChoices().get(0).getMessage().getContent();
    }

    private static GenerationParam createGenerationParam(List<ConversationHistory.Message> history, String apiKey, boolean includeSystemPrompt, String prompt, String modelName, float temperature) {
        List<Message> messages = new ArrayList<>(messageHistory); // 使用滚动窗口内的消息
        // 判断对话历史是否为空
        history.forEach(item -> {
            Message message = new Message();
            message.setRole(item.getRole());
            message.setContent(item.getContent());
            messages.add(message);
        });
        GenerationParam paramBuilder = GenerationParam.builder()
                .model(modelName)
                .messages(messages)
                .resultFormat(GenerationParam.ResultFormat.MESSAGE)
                .apiKey(apiKey)
                .topK(50)
                .temperature(temperature)
                .topP(0.8)
                .seed(1234)
                .build();

        if (includeSystemPrompt) {
            // 如果为空报错
            String promptStr = Optional.ofNullable(prompt)
                    .filter(StringUtils::isNotBlank)
                    .orElseThrow(() -> new IllegalArgumentException("Prompt cannot be empty."));
            // 添加系统提示词消息
            Message systemMessage = Message.builder()
                    .role(Role.SYSTEM.getValue())
                    .content(promptStr)
                    .build();
            // 系统提示词作为对话历史的第一条消息
            messages.add(0, systemMessage);
            paramBuilder.setMessages(messages);
        }
        return paramBuilder;
    }
}