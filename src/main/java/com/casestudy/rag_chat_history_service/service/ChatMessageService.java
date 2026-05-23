package com.casestudy.rag_chat_history_service.service;

import com.casestudy.rag_chat_history_service.dto.request.AddMessageRequest;
import com.casestudy.rag_chat_history_service.dto.response.ChatMessageResponse;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface ChatMessageService {

    ChatMessageResponse addMessage(UUID sessionId , AddMessageRequest request);

    Page<ChatMessageResponse> getMessage(UUID sessionId, int page, int size);
}
