package com.alibaba.cloud.ai.memory.client;

import com.alibaba.cloud.ai.memory.conversation.WindowMemory;

public abstract class AbstractModelClient {

    public final WindowMemory conversationMemory = new WindowMemory();

    protected String apiKey;
    protected int windowSize;
    protected double temperature;
    protected String modelName;
    protected String prompt;
    protected boolean includeSystemPrompt;

    public AbstractModelClient(String apiKey, int windowSize, double temperature, String modelName, boolean includeSystemPrompt, String prompt) {
        this.apiKey = apiKey;
        this.windowSize = windowSize;
        this.temperature = temperature;
        this.modelName = modelName;
        this.prompt = prompt;
        this.includeSystemPrompt = includeSystemPrompt;
    }

    public String getApiKey() {
        return apiKey;
    }

    public int getWindowSize() {
        return windowSize;
    }

    public double getTemperature() {
        return temperature;
    }

    public String getModelName() {
        return modelName;
    }

    public String getPrompt() {
        return prompt;
    }

    public boolean isIncludeSystemPrompt() {
        return includeSystemPrompt;
    }
}
