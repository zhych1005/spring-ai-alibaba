package com.alibaba.cloud.ai.memory.client;

import com.alibaba.cloud.ai.memory.conversation.ChatMessage;
import com.alibaba.cloud.ai.memory.conversation.WindowMemory;
import com.alibaba.dashscope.aigc.generation.Generation;
import com.alibaba.dashscope.aigc.generation.GenerationParam;
import com.alibaba.dashscope.aigc.generation.GenerationResult;
import com.alibaba.dashscope.common.Message;
import com.alibaba.dashscope.common.Role;
import com.alibaba.dashscope.exception.ApiException;
import com.alibaba.dashscope.exception.InputRequiredException;
import com.alibaba.dashscope.exception.NoApiKeyException;
import io.micrometer.common.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class QwenClient extends AbstractModelClient{

    private static final Generation gen = new Generation();

    public QwenClient(String apiKey, int windowSize, double temperature, String modelName, boolean includeSystemPrompt, String prompt) {
        super(apiKey, windowSize, temperature, modelName, includeSystemPrompt, prompt);
    }

    public String call(String userInput) throws ApiException, NoApiKeyException, InputRequiredException {
        if (StringUtils.isBlank(apiKey)) {
            throw new IllegalStateException("API key is not set.");
        }
        // 获取记忆、添加记忆。
        super.addUserBufferWindowMemoryMessage(userInput);
        String response = generateResponse(conversationMemory.memory, apiKey, includeSystemPrompt, prompt, modelName, temperature);
        super.addAssistantBufferWindowMemoryMessage(response);
        return response;
    }

    // 生成 Qwen 响应
    public static String generateResponse(String apiKey, boolean includeSystemPrompt, String prompt, String modelName, float temperature) throws ApiException, NoApiKeyException, InputRequiredException {
        // todo 获取对话历史
        List<ChatMessage> messages = new ArrayList<>();
        // 创建生成参数并调用 API
        GenerationParam param = createGenerationParam(messages, apiKey, includeSystemPrompt, prompt, modelName, temperature);
        GenerationResult result = gen.call(param);
        // 获取模型响应
        return result.getOutput().getChoices().get(0).getMessage().getContent();
    }

    private static GenerationParam createGenerationParam(List<ChatMessage> memory, String apiKey, boolean includeSystemPrompt, String prompt, String modelName, float temperature) {
        List<ChatMessage> messages = new ArrayList<>(); // 使用滚动窗口内的消息
        // 判断对话历史是否为空
        memory.forEach(item -> {
            ChatMessage message = new ChatMessage();
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
