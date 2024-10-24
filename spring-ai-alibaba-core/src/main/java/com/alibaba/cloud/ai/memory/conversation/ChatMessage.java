package com.alibaba.cloud.ai.memory.conversation;

public class ChatMessage {

    private String role;
    private String content;

    public ChatMessage(String role, String content) {
        this.role = role;
        this.content = content;
    }


    // 必须提供无参构造函数以供 FastJSON 反序列化
    public ChatMessage() {}

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}