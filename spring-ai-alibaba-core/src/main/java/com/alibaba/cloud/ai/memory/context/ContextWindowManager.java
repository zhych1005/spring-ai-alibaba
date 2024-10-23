package com.alibaba.cloud.ai.memory.context;

public interface ContextWindowManager {
    void addMemory(String role, String message);
    void setApiKey(String apiKey);
    void setModelName(String modelName);
    void setTemperature(Float temperature);
    void setPrompt(String prompt);
    void setIncludeSystemPrompt(Boolean includeSystemPrompt);
    void setWindowSize(Integer windowSize);
}