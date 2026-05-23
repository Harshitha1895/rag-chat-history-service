package com.casestudy.rag_chat_history_service.dto.request;

import jakarta.validation.constraints.NotBlank;

public record RenameSessionRequest(
        @NotBlank String title
) {
}
