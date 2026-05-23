package com.casestudy.rag_chat_history_service.exception;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ErrorResponse (
        int status,
        String error,
        String message,
        String path,
        LocalDateTime timeStamp
){
}
