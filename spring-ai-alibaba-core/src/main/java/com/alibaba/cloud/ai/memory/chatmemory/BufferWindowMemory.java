package com.alibaba.cloud.ai.memory.chatmemory;

import com.alibaba.cloud.ai.memory.adapter.PersistenceAdapter;
import com.alibaba.cloud.ai.memory.entity.ChatMemoryProperties;
import com.alibaba.cloud.ai.memory.entity.ChatMessage;
import com.alibaba.cloud.ai.memory.enums.RoleTypeEnum;
import com.alibaba.cloud.ai.memory.handler.MemoryHandler;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class BufferWindowMemory implements MemoryHandler {

	@Override
	public void addMessage(String conversationId, ChatMessage message, String question,
			PersistenceAdapter persistenceAdapter, ChatMemoryProperties properties) {
		ChatMessage inputMessage = ChatMessage.builder()
				.role(RoleTypeEnum.USER.getRoleName())
				.content(question)
				.inputTokens(message.getInputTokens())
				.createdAt(System.currentTimeMillis())
				.build();
		message.setCreatedAt(System.currentTimeMillis());
		List<ChatMessage> messages = persistenceAdapter.getMessages(conversationId, 0);
		messages.add(inputMessage);
		messages.add(message);
		persistenceAdapter.saveMessage(conversationId, messages);
	}

}