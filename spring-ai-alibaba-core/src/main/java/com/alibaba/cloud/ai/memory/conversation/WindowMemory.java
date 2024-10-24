package com.alibaba.cloud.ai.memory.conversation;

import java.util.List;

public class WindowMemory {

    public static void addBufferWindowMemoryMessage(List<ChatMessage> memory, String role, String content, int windowSize) {
        memory.add(new ChatMessage(role, content));
        if (memory.size() >= windowSize * 2) {
            memory.subList(0, memory.size() - windowSize * 2).clear();
        }
    }
}