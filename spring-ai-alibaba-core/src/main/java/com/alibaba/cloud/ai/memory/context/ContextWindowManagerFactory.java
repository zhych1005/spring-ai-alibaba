package com.alibaba.cloud.ai.memory.context;


import com.alibaba.cloud.ai.memory.conversation.OpenAIContextWindowManager;
import com.alibaba.cloud.ai.memory.conversation.QwenContextWindowManager;

public class ContextWindowManagerFactory {

    private ContextWindowManagerFactory() {}

    /**
     * 根据模型类型、窗口大小、API 密钥和是否包括系统提示词来创建上下文窗口管理器。
     * @param windowSize             上下文窗口大小
     * @param apiKey                 API 密钥
     * @param includeSystemPrompt    是否包括系统提示词
     * @return 创建的上下文窗口管理器实例
     */
    public static QwenContextWindowManager createQwenManager(int windowSize, Float temperature, String apiKey, boolean includeSystemPrompt, String prompt, String modelName) {
        QwenContextWindowManager qwenManager = new QwenContextWindowManager();
        qwenManager.setApiKey(apiKey);
        qwenManager.setModelName(modelName);
        qwenManager.setTemperature(temperature);
        qwenManager.setPrompt(prompt);
        qwenManager.setWindowSize(windowSize);
        qwenManager.setIncludeSystemPrompt(includeSystemPrompt);
        return qwenManager;
    }

    public static OpenAIContextWindowManager createOpenAIManager(int windowSize, String apiKey, boolean includeSystemPrompt) {
        OpenAIContextWindowManager openAIManager = new OpenAIContextWindowManager();
        openAIManager.setApiKey(apiKey);
        return openAIManager;
    }
}