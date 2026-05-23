package com.casestudy.rag_chat_history_service.service;

import com.casestudy.rag_chat_history_service.dto.request.CreateSessionRequest;
import com.casestudy.rag_chat_history_service.dto.response.ChatSessionResponse;

import java.util.List;
import java.util.UUID;

public interface ChatSessionService {

    ChatSessionResponse createChatSession(CreateSessionRequest createSessionRequest);

    List<ChatSessionResponse> getChatSessions(String userId);

    ChatSessionResponse renameSession(UUID sessionId,String title);

    ChatSessionResponse markAsFavorite(UUID sessionId);

    void deleteSession(UUID sessionId);
}
