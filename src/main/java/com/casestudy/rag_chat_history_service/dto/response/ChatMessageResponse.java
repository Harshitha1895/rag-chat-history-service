package com.casestudy.rag_chat_history_service.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

public record ChatMessageResponse(
        UUID id,
        String sender,
        String content,
        String retrievedContext,
        LocalDateTime createdAt
) {
}
