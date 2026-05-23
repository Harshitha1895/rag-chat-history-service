package com.casestudy.rag_chat_history_service.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

public record ChatSessionResponse(
        UUID id,
        String userId,
        String title,
        String favorite,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
