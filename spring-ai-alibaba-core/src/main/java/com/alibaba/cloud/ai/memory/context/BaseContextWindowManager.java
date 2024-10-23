package com.alibaba.cloud.ai.memory.context;


import com.alibaba.cloud.ai.memory.history.ConversationHistory;

public abstract class BaseContextWindowManager implements ContextWindowManager {
    public final ConversationHistory conversationHistory = new ConversationHistory();
    protected String apiKey;
    protected int windowSize;
    protected String modelName;
    protected Float temperature;
    protected String prompt;
    protected Boolean includeSystemPrompt;

    @Override
    public void addMemory(String role, String message) {
        conversationHistory.addBufferWindowMemoryMessage(role, message, windowSize);
    }


    @Override
    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    @Override
    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    @Override
    public void setTemperature(Float temperature) {
        this.temperature = temperature;
    }

    @Override
    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    @Override
    public void setIncludeSystemPrompt(Boolean includeSystemPrompt) {
        this.includeSystemPrompt = includeSystemPrompt;
    }

    @Override
    public void setWindowSize(Integer windowSize) {
        this.windowSize = windowSize;
    }
}
